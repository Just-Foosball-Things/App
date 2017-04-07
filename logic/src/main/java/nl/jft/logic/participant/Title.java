package nl.jft.logic.participant;

import java.io.Serializable;

import nl.jft.common.Identifiable;
import nl.jft.common.util.Arguments;
import nl.jft.logic.LogicConstants;

/**
 * A {@code Title} is an estethic label given to a {@link nl.jft.logic.participant.impl.User} under certain conditions.
 *
 * @author Lesley
 */
public class Title implements Identifiable, Serializable {

    private final int id;
    private final String name;

    /**
     * Initializes a new {@code Title} by invoking an other constructor ({@link #Title(int, String)}. The {@code id} used is {@link LogicConstants#INTERNAL_ID}.
     *
     * @param name The {@code name} of this {@code Title}, should not be {@code null} or empty.
     * @throws NullPointerException     If {@code name} is {@code null}.
     * @throws IllegalArgumentException If {@code name} is empty.
     */
    public Title(String name) {
        this(LogicConstants.INTERNAL_ID, name);
    }

    /**
     * Initiates a new {@code Title} using the given {@code id} and {@code name}.
     *
     * @param id   The {@code id} of this {@code Title}, used by external layers.
     * @param name The {@code name} of this {@code Title}, should not be {@code null} or empty.
     * @throws NullPointerException     If {@code name} is {@code null}.
     * @throws IllegalArgumentException If {@code name} is empty.
     */
    public Title(int id, String name) {
        this.id = id;
        this.name = Arguments.requireNotEmpty(name);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Title)) {
            return false;
        }

        Title o = (Title) other;
        return o.id == id && o.name.equals(name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets the {@code name} of this {@code Title}.
     *
     * @return The {@code name} of this {@code Title}.
     */
    public String getName() {
        return name;
    }

}
