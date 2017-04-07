package nl.jft.network.message;

import nl.jft.network.Connection;

/**
 * A {@code MessageHandler} handles a {@link Message} received by an {@link nl.jft.network.EndPoint}.
 *
 * @author Lesley
 */
public abstract class MessageHandler<M extends Message> {

    /**
     * Handles a {@link Message} received by an {@link nl.jft.network.EndPoint}.
     *
     * @param connection The {@link Connection} that received the given {@code Message}, should not be {@code null}.
     * @param message    The {@code Message} received by an {@code EndPoint}, should not be {@code null.}
     */
    public abstract void handle(Connection connection, M message);

    /**
     * Gets the {@link Class} of the underlying {@link Message} that this {@code MessageHandler} handles.
     *
     * @return The {@code Class} of the underlying {@code Message} that this {@code MessageHandler} handles.
     */
    public abstract Class<M> getType();

}
