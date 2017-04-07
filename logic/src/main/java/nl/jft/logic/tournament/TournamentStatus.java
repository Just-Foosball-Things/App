package nl.jft.logic.tournament;

/**
 * A {@code TournamentStatus} provides an indication in what kind of state a {@link Tournament} is.
 *
 * @author Lesley
 */
public enum TournamentStatus {

    /**
     * The initial state of a {@link Tournament}, nothing has happened yet and {@link nl.jft.logic.participant.Participant participants}
     * may be added or removed. A {@code Tournament} can be started from this state only.
     */
    SETUP,

    /**
     * Indicates that a {@link Tournament} has been started. {@link nl.jft.logic.participant.Participant participants}
     * may no longer be added or removed. A {@code Tournament} can be stopped from this state only.
     */
    IN_PROGRESS,

    /**
     * Indicates that a {@link Tournament} has finished. {@link nl.jft.logic.participant.Participant participants}
     * may not be added or removed. A {@code Tournament} now has a winner.
     */
    FINISHED,

    /**
     * Indicates that a {@link Tournament} has been prematurely stopped.
     */
    ABORTED

}
