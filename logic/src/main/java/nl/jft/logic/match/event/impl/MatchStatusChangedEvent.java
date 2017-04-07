package nl.jft.logic.match.event.impl;

import java.util.Objects;

import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchStatus;
import nl.jft.logic.match.event.MatchEvent;

/**
 * A {@code MatchStatusChangedEvent} occurs whenever the {@link MatchStatus} of a {@link Match} changes.
 *
 * @author Lesley
 */
public final class MatchStatusChangedEvent extends MatchEvent {

    private final MatchStatus oldStatus;
    private final MatchStatus newStatus;

    /**
     * Initializes a new {@code MatchStatusChangedEvent} using the given arguments.
     *
     * @param match     The {@link Match} that this event occurred in, should not be {@code null}.
     * @param oldStatus The {@link MatchStatus} that was active before the change, should not be {@code null}.
     * @param newStatus The new {@code MatchStatus} of the {@code Match}, should not be {@code null}.
     * @throws NullPointerException If the given {@code Match} is {@code null}.
     * @throws NullPointerException If the given {@code oldStatus} is {@code null}.
     * @throws NullPointerException If the given {@code newStatus} is {@code null}.
     */
    public MatchStatusChangedEvent(Match match, MatchStatus oldStatus, MatchStatus newStatus) {
        super(match);

        this.oldStatus = Objects.requireNonNull(oldStatus);
        this.newStatus = Objects.requireNonNull(newStatus);
    }

    /**
     * Gets the {@link MatchStatus} that was active before the change.
     *
     * @return The {@code MatchStatus} that was active before the change.
     */
    public MatchStatus getOldStatus() {
        return oldStatus;
    }

    /**
     * Gets the new {@link MatchStatus} of the {@link Match}.
     *
     * @return The new {@code MatchStatus} of the {@code Match}.
     */
    public MatchStatus getNewStatus() {
        return newStatus;
    }

}
