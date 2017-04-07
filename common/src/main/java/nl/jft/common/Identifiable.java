package nl.jft.common;

/**
 * Represents an {@link Object} which can be identified by an id (see {@code getId()}).
 *
 * @author Lesley Vente
 */
@FunctionalInterface
public interface Identifiable {

    /**
     * Gets the id of this {@code Identifiable}.
     *
     * @return The id of this {@code Identifiable}.
     */
    int getId();

}
