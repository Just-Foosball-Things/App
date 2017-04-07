package nl.jft.network;

import java.net.SocketAddress;

import nl.jft.network.message.Message;

/**
 * @author Lesley
 */
public interface Connection {

    /**
     * Writes a {@link Message} to this {@code Connection}.
     *
     * @param message The {@code Message} to write to this {@code Connection}, should not be {@code null}.
     * @throws NullPointerException If the given {@code message} is {@code null}.
     */
    void write(Message message);

    /**
     * Closes this {@code Connection} so that no {@link Message messages} can be sent or received.
     */
    void close();

    /**
     * Gets the local address of this {@code Connection}.
     *
     * @return A {@link SocketAddress} containing information about the local address of this {@code Connection}.
     */
    SocketAddress getLocalAddress();

    /**
     * Gets the remote address of this {@code Connection}.
     *
     * @return A {@link SocketAddress} containing information about the remote address of this {@code Connection}.
     */
    SocketAddress getRemoteAddress();

    /**
     * Returns whether this {@code Connection} is {@code Active} or not. A {@code Connection} is considered {@code Active}
     * if, and only if, it can send and receive {@link Message messages}.
     *
     * @return True if, and only if, this {@code Connection} is {@code Active}.
     */
    boolean isActive();

}
