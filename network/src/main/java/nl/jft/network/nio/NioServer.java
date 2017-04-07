package nl.jft.network.nio;

import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Objects;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import nl.jft.network.AbstractEndPoint;
import nl.jft.network.Server;
import nl.jft.network.message.MessageHandlerChainSet;

/**
 * A {@code NioServer} is a non-blocking implementation of {@link nl.jft.network.Server} using Netty.
 *
 * @author Lesley
 */
public final class NioServer extends AbstractEndPoint implements Server {

    private Channel channel;
    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;

    /**
     * Initializes a {@code NioServer} using the default constructor in {@link AbstractEndPoint} ({@link AbstractEndPoint#AbstractEndPoint()}.
     */
    public NioServer() {
        // Default constructor is allowed.
    }

    /**
     * Initializes a {@code NioServer} using the default constructor in {@link AbstractEndPoint} ({@link AbstractEndPoint#AbstractEndPoint(FSTConfiguration, MessageHandlerChainSet)}.
     */
    public NioServer(FSTConfiguration serializer, MessageHandlerChainSet chainSet) {
        super(serializer, chainSet);
    }

    @Override
    public void bind(SocketAddress address) throws IOException {
        Objects.requireNonNull(address);

        close();

        try {
            SelfSignedCertificate certificate = new SelfSignedCertificate();
            SslContext context = SslContextBuilder.forServer(certificate.certificate(), certificate.privateKey()).build();

            parentGroup = new NioEventLoopGroup();
            childGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.group(parentGroup, childGroup);
            bootstrap.childHandler(new NioServerInitializer(this, context));

            channel = bootstrap.bind(address).syncUninterruptibly().channel();
        } catch (Exception e) {
            close();

            throw new IOException("Could not bind this server on the given port.", e);
        }
    }

    @Override
    public void close() {
        if (parentGroup != null) {
            parentGroup.shutdownGracefully().syncUninterruptibly();
            parentGroup = null;
        }

        if (childGroup != null) {
            childGroup.shutdownGracefully().syncUninterruptibly();
            childGroup = null;
        }
    }

}
