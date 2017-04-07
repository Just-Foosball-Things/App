package nl.jft.logic.match.event;

import nl.jft.logic.match.Match;
import nl.jft.logic.match.event.impl.GoalRemovedEvent;
import nl.jft.logic.match.event.impl.GoalScoredEvent;
import nl.jft.logic.match.event.impl.MatchStatusChangedEvent;

/**
 * A {@code MatchListener} can be used to get notified about certain events in a {@link Match}.
 *
 * @author Lesley
 */
public interface MatchListener {

    /**
     * The {@code onMatchStatusChanged} method gets called whenever a {@link nl.jft.logic.match.MatchStatus}
     * change occurs in a {@link Match}. This method blocks, so make sure to keep the return time as low as possible.
     *
     * @param event The {@link MatchStatusChangedEvent} that occurred in the {@code Match}.
     */
    void onMatchStatusChanged(MatchStatusChangedEvent event);

    /**
     * The {@code onGoalScored} method gets called whenever a {@link nl.jft.logic.match.Goal}
     * is scored in a {@link Match}. This method blocks, so make sure to keep the return time as low as possible.
     *
     * @param event The {@link GoalScoredEvent} that occurred in the {@code Match}.
     */
    void onGoalScored(GoalScoredEvent event);

    /**
     * The {@code onGoalRemoved} method gets called whenever a {@link nl.jft.logic.match.Goal}
     * is removed from a {@link Match}. This method blocks, so make sure to keep the return time as low as possible.
     *
     * @param event The {@link GoalRemovedEvent} that occurred in the {@code Match}.
     */
    void onGoalRemoved(GoalRemovedEvent event);

}
