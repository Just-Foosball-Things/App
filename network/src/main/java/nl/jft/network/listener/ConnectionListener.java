package nl.jft.network.listener;

import nl.jft.network.Connection;

/**
 * A {@code ConnectionListener} can be added to a {@link nl.jft.network.EndPoint} to get notified of certain events
 * regarding {@link Connection connections}.
 *
 * @author Lesley
 */
public interface ConnectionListener {

    /**
     * Gets invoked whenever a {@link Connection} gets {@code Active}. See {@link Connection#isActive()}
     *
     * @param connection The {@code Connection} that is now {@code Active}.
     */
    void connectionActive(Connection connection);

    /**
     * Gets invoked whenever a {@link Connection} gets {@code Inactive}. See {@link Connection#isActive()}
     *
     * @param connection The {@code Connection} that is now {@code Inactive}.
     */
    void connectionInactive(Connection connection);

}
