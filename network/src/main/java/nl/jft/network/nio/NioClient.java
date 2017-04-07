package nl.jft.network.nio;

import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Objects;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import nl.jft.network.AbstractEndPoint;
import nl.jft.network.Client;
import nl.jft.network.message.MessageHandlerChainSet;

/**
 * A {@code NioClient} is a non-blocking implementation of {@link nl.jft.network.Client} using Netty.
 *
 * @author Lesley
 */
public final class NioClient extends AbstractEndPoint implements Client {

    private Channel channel;
    private EventLoopGroup group;

    /**
     * Initializes a {@code NioClient} using the default constructor in {@link AbstractEndPoint} ({@link AbstractEndPoint#AbstractEndPoint()}.
     */
    public NioClient() {
        // Default constructor is allowed.
    }

    /**
     * Initializes a {@code NioClient} using the default constructor in {@link AbstractEndPoint} ({@link AbstractEndPoint#AbstractEndPoint(FSTConfiguration, MessageHandlerChainSet)}.
     */
    public NioClient(FSTConfiguration serializer, MessageHandlerChainSet chainSet) {
        super(serializer, chainSet);
    }

    @Override
    public void connect(SocketAddress address) throws IOException {
        Objects.requireNonNull(address);

        close();

        try {
            SslContext context = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();

            group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new NioClientInitializer(this, context));

            channel = bootstrap.connect(address).syncUninterruptibly().channel();
        } catch (Exception e) {
            close();

            throw new IOException("Could not connect this client to the given host and port.", e);
        }
    }

    @Override
    public void close() {
        if (group != null) {
            group.shutdownGracefully().syncUninterruptibly();
            group = null;
        }
    }

}
