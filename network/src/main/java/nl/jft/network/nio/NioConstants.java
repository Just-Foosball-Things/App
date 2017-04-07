package nl.jft.network.nio;

import io.netty.util.AttributeKey;
import nl.jft.network.Connection;

/**
 * The {@code NioConstants} class defines constants used by the NIO-implementation of the network.
 *
 * @author Lesley
 */
public final class NioConstants {

    /**
     * The {@link AttributeKey} that is used to fetch a {@link Connection} from a {@link io.netty.channel.Channel}.
     */
    public static final AttributeKey<Connection> ATTRIBUTE_CONNECTION = AttributeKey.valueOf("connection");

    private NioConstants() {
        throw new UnsupportedOperationException("Should not be called.");
    }

}
