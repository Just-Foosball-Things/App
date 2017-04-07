package nl.jft.network.nio;

import java.util.Objects;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * A {@code NioClientInitializer} sets up the pipeline for a {@link NioClient} according to the Netty specification.
 *
 * @author Lesley
 */
final class NioClientInitializer extends ChannelInitializer<SocketChannel> {

    private final NioClient client;
    private final SslContext context;

    /**
     * Initializes this {@code NioClientInitializer} using the given {@link nl.jft.network.EndPoint} and {@link SslContext}.
     *
     * @param client  The {@link NioClient EndPoint} used to serialize {@link nl.jft.network.message.Message messages},
     *                should not be {@code null}.
     * @param context The {@code SslContext} used to encrypt {@code Messages}, should not be {@code null}.
     * @throws NullPointerException If the given {@code NioClient} is {@code null}.
     * @throws NullPointerException If the given {@code SslContext} is {@code null}.
     */
    NioClientInitializer(NioClient client, SslContext context) {
        this.client = Objects.requireNonNull(client);
        this.context = Objects.requireNonNull(context);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(context.newHandler(ch.alloc()));
        pipeline.addLast(new MessageCodec(client));
        pipeline.addLast(new NioHandler(client));
    }

}
