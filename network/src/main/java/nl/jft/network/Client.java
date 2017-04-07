package nl.jft.network;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * A {@code Client} connects to a remote-{@code EndPoint}.
 *
 * @author Lesley
 */
public interface Client extends EndPoint {

    /**
     * Connects this {@code Client} to the remote-{@code EndPoint} on the specified {@code address}.
     *
     * @param address The {@code address} on which the remote-{@code EndPoint} can be found, should not be {@code null}.
     * @throws IOException          If this {@code EndPoint} could not connect to the remote-{@code EndPoint}.
     * @throws NullPointerException If the given {@code address} is {@code null}.
     */
    void connect(SocketAddress address) throws IOException;

    @Override
    void close();

}
