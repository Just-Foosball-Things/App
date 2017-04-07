package nl.jft.logic.match.event.impl;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.match.event.GoalEvent;

/**
 * A {@code GoalRemovedEvent} is an event that occurs whenever a {@link Goal} was removed from a {@link Match}.
 *
 * @author Lesley
 */
public final class GoalRemovedEvent extends GoalEvent {

    /**
     * Initializes a new {@code GoalRemovedEvent} using the given arguments.
     *
     * @param match The {@link Match} that this event occurred in, should not be {@code null}.
     * @param goal  The {@link Goal} that was removed from the given {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the given {@code Match} is {@code null}.
     * @throws NullPointerException If the given {@code Goal} is {@code null}.
     */
    public GoalRemovedEvent(Match match, Goal goal) {
        super(match, goal);
    }

}
