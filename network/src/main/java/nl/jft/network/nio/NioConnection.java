package nl.jft.network.nio;

import java.net.SocketAddress;
import java.util.Objects;

import io.netty.channel.Channel;
import nl.jft.network.Connection;
import nl.jft.network.message.Message;

/**
 * A {@code NioConnection} is a NIO-implementation of {@link Connection} using Netty.
 *
 * @author Lesley
 */
public final class NioConnection implements Connection {

    private final Channel channel;

    /**
     * Initializes this {@code NioConnection} using the given {@link Channel}.
     *
     * @param channel The {@code Channel} that Netty uses for communication, should not be {@code null}.
     * @throws NullPointerException If the given {@code Channel} is {@code null}.
     */
    NioConnection(Channel channel) {
        this.channel = Objects.requireNonNull(channel);
    }

    @Override
    public void write(Message message) {
        Objects.requireNonNull(message);

        channel.writeAndFlush(message);
    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public SocketAddress getLocalAddress() {
        return channel.localAddress();
    }

    @Override
    public SocketAddress getRemoteAddress() {
        return channel.remoteAddress();
    }

    @Override
    public boolean isActive() {
        return channel.isActive();
    }

}
