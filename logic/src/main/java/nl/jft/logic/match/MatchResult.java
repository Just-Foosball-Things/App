package nl.jft.logic.match;

import java.util.Objects;

import nl.jft.common.Identifiable;
import nl.jft.logic.LogicConstants;

/**
 * A {@code MatchResult} represents the result of a {@link Match}. It is of the type {@link Identifiable} so that it can be stored in a database.
 *
 * @author Lesley
 */
public class MatchResult implements Identifiable {

    private final int id;
    private final Match match;

    /**
     * Initializes a new {@code MatchResult} by invoking an other constructor ({@link #MatchResult(int, Match)}.
     *
     * @param match The {@link Match} that was played, should not be {@code null}.
     * @throws NullPointerException If {@code Match} is {@code null}.
     */
    public MatchResult(Match match) {
        this(LogicConstants.INTERNAL_ID, match);
    }

    /**
     * Initializes a new {@code MatchResult} using the given arguments.
     *
     * @param id    The id of this {@code MatchResult}, used by external parties to identify this {@code MatchResult}.
     * @param match The {@link Match} that was played, should not be {@code null}.
     * @throws NullPointerException If {@code Match} is {@code null}.
     */
    public MatchResult(int id, Match match) {
        this.id = id;
        this.match = Objects.requireNonNull(match);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MatchResult)) {
            return false;
        }

        MatchResult o = (MatchResult) other;
        return o.id == id && o.match.equals(match);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + match.hashCode();
        return result;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets the {@link Match} that was played.
     *
     * @return A {@link Match} object containing the {@link nl.jft.logic.participant.Participant participants} and {@link Goal goals} scored.
     */
    public Match getMatch() {
        return match;
    }

}
