package nl.jft.network;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * A {@code Server} accepts {@code connections} from a remote-{@code EndPoint}.
 *
 * @author Lesley
 */
public interface Server extends EndPoint {

    /**
     * Binds this {@code Server} to accept {@code connections} from another {@code EndPoint}.
     *
     * @param address The {@code address} on which this {@code Server} should be bound, should not be {@code null}.
     * @throws IOException          If this {@code EndPoint} could not be bound on the given {@code address}.
     * @throws NullPointerException If the given {@code address} is {@code null}.
     */
    void bind(SocketAddress address) throws IOException;

    @Override
    void close();

}
