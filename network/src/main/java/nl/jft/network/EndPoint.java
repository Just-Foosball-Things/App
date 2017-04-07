package nl.jft.network;

import org.nustaq.serialization.FSTConfiguration;

import java.util.List;

import nl.jft.network.listener.ConnectionListener;
import nl.jft.network.message.MessageHandlerChainSet;

/**
 * An {@code EndPoint} is either a {@link Client} or {@link Server} and so takes care of connecting
 * to another {@code EndPoint}, or accepting {@code connections} from another {@code EndPoint}.
 *
 * @author Lesley
 */
public interface EndPoint {

    /**
     * Closes this {@code EndPoint} so that all {@code connections} are closed.
     */
    void close();

    /**
     * Gets the {@code Serializer} to serialize {@link nl.jft.network.message.Message messages}.
     *
     * @return An {@link FSTConfiguration} containing all the information needed to serialize a {@code Message}.
     */
    FSTConfiguration getFstConfiguration();

    /**
     * Gets the {@link MessageHandlerChainSet} for this {@link EndPoint}.
     *
     * @return The {@code MessageHandlerChainSet}.
     */
    MessageHandlerChainSet getMessageHandlers();

    /**
     * Adds a {@link ConnectionListener} to this {@code EndPoint.}
     *
     * @param listener The {@code listener} to add, should not be {@code null}.
     * @throws NullPointerException If the given {@code listener} is {@code null}.
     */
    void addListener(ConnectionListener listener);

    /**
     * Removes a {@link ConnectionListener} from this {@code EndPoint.}
     *
     * @param listener The {@code listener} to remove, should not be {@code null}.
     * @throws NullPointerException If the given {@code listener} is {@code null}.
     */
    void removeListener(ConnectionListener listener);

    /**
     * Gets all {@link ConnectionListener listeners} listening on this {@code EndPoint}.
     *
     * @return A {@link List} containign all {@code ConnectionListeners} listening on this {@code EndPoint}.
     */
    List<ConnectionListener> getListeners();

}
