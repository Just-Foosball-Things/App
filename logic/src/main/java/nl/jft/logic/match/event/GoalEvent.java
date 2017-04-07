package nl.jft.logic.match.event;

import java.util.Objects;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;

/**
 * A {@code GoalEvent} is an event that occurs in a {@link Match} whenever a {@link Goal} is added or removed.
 *
 * @author Lesley
 */
public abstract class GoalEvent extends MatchEvent {

    private final Goal goal;

    /**
     * Initializes a new {@code GoalEvent} using the given arguments.
     *
     * @param match The {@link Match} that this event occurred in, should not be {@code null}.
     * @param goal  The {@link Goal} that was added or removed from the given {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the given {@code Match} is {@code null}.
     * @throws NullPointerException If the given {@code Goal} is {@code null}.
     */
    public GoalEvent(Match match, Goal goal) {
        super(match);

        this.goal = Objects.requireNonNull(goal);
    }

    /**
     * Gets the {@link Goal} that was added or removed from the {@link Match}.
     *
     * @return The {@code Goal} that was added or removed from the {@code Match}.
     */
    public Goal getGoal() {
        return goal;
    }

}
