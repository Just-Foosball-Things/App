package nl.jft.logic.match;

import java.io.Serializable;

import nl.jft.common.Identifiable;
import nl.jft.common.util.Arguments;
import nl.jft.logic.LogicConstants;

/**
 * A {@code Rule} defines what should and should not be allowed during a {@link Match}.
 *
 * @author Lesley
 */
public class Rule implements Identifiable, Serializable {

    private final int id;
    private final String description;

    /**
     * Initializes a new {@code Rule} by invoking an other constructor ({@link #Rule(int, String)}. The {@code id} used is {@link LogicConstants#INTERNAL_ID}.
     *
     * @param description The {@code description} of this {@code Rule}, this contains the actual definition
     *                    of this {@code Rule}, should not be {@code null} or empty.
     * @throws NullPointerException     If the {@code description} is {@code null}.
     * @throws IllegalArgumentException If the {@code description} is empty.
     */
    public Rule(String description) {
        this(LogicConstants.INTERNAL_ID, description);
    }

    /**
     * Initiates a new {@code Rule} using the given {@code id} and {@code description}.
     *
     * @param id          The {@code id} of this {@code Rule}, used by external layers.
     * @param description The {@code description} of this {@code Rule}, this contains the actual definition
     *                    of this {@code Rule}, should not be {@code null} or empty.
     * @throws NullPointerException     If the {@code description} is {@code null}.
     * @throws IllegalArgumentException If the {@code description} is empty.
     */
    public Rule(int id, String description) {
        this.id = id;
        this.description = Arguments.requireNotEmpty(description);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Rule)) {
            return false;
        }

        Rule o = (Rule) other;
        return o.id == id && o.description.equals(description);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets the {@code description} of this {@code Rule}.
     *
     * @return The {@code description} of this {@code Rule}.
     */
    public String getDescription() {
        return description;
    }

}
