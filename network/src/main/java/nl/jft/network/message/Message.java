package nl.jft.network.message;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A {@code Message} represents data getting sent or received between two {@code connections}.
 *
 * @author Lesley
 */
public abstract class Message implements Serializable {

    private final AtomicBoolean terminated = new AtomicBoolean(false);

    /**
     * Terminates this {@code message} so that it no longer gets handled.
     */
    public final void terminate() {
        terminated.set(true);
    }

    /**
     * Tell whether this {@code terminated} is {@code terminated} or not.
     *
     * @return True if, and only if, this {@code message} is {@code terminated}.
     */
    public boolean isTerminated() {
        return terminated.get();
    }

}
