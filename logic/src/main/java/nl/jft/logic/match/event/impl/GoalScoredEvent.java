package nl.jft.logic.match.event.impl;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.match.event.GoalEvent;

/**
 * A {@code GoalScoredEvent} is an event that occurs whenever a {@link Goal} was scored in a {@link Match}.
 *
 * @author Lesley
 */
public final class GoalScoredEvent extends GoalEvent {

    /**
     * Initializes a new {@code GoalScoredEvent} using the given arguments.
     *
     * @param match The {@link Match} that this event occurred in, should not be {@code null}.
     * @param goal  The {@link Goal} that was added to the given {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the given {@code Match} is {@code null}.
     * @throws NullPointerException If the given {@code Goal} is {@code null}.
     */
    public GoalScoredEvent(Match match, Goal goal) {
        super(match, goal);
    }
}
