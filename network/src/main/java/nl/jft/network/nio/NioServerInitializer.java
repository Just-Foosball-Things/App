package nl.jft.network.nio;

import java.util.Objects;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * A {@code NioServerInitializer} sets up the pipeline for a {@link NioServer} according to the Netty specification.
 *
 * @author Lesley
 */
final class NioServerInitializer extends ChannelInitializer<SocketChannel> {

    private final NioServer server;
    private final SslContext context;

    /**
     * Initializes this {@code NioServerInitializer} using the given {@link nl.jft.network.EndPoint} and {@link SslContext}.
     *
     * @param server  The {@link NioServer EndPoint} used to serialize {@link nl.jft.network.message.Message messages},
     *                should not be {@code null}.
     * @param context The {@code SslContext} used to encrypt {@code Messages}, should not be {@code null}.
     * @throws NullPointerException If the given {@code NioServer} is {@code null}.
     * @throws NullPointerException If the given {@code SslContext} is {@code null}.
     */
    NioServerInitializer(NioServer server, SslContext context) {
        this.server = Objects.requireNonNull(server);
        this.context = Objects.requireNonNull(context);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(context.newHandler(ch.alloc()));
        pipeline.addLast(new MessageCodec(server));
        pipeline.addLast(new NioHandler(server));
    }


}
