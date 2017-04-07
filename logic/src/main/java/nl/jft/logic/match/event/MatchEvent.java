package nl.jft.logic.match.event;

import java.io.Serializable;
import java.util.Objects;

import nl.jft.logic.match.Match;

/**
 * A {@code MatchEvent} is an event that occurs whenever something happens in a {@link Match}.
 *
 * @author Lesley
 */
public abstract class MatchEvent implements Serializable {

    private final Match match;

    /**
     * Initializes a new {@code MatchEvent} using the given {@link Match} as argument.
     *
     * @param match The {@code Match} that this event occurred in, should not be {@code null}.
     * @throws NullPointerException If the given {@code Match} is {@code null}.
     */
    public MatchEvent(Match match) {
        this.match = Objects.requireNonNull(match);
    }

    /**
     * Gets the {@code Match} that this event occurred in.
     *
     * @return The {@code Match} that this event occurred in.
     */
    public final Match getMatch() {
        return match;
    }

}
