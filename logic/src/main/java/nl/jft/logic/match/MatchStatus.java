package nl.jft.logic.match;

/**
 * A {@code MatchStatus} provides an indication in what kind of state a {@link Match} is.
 *
 * @author Lesley
 */
public enum MatchStatus {

    /**
     * The initial state of a {@link Match}, nothing significant has happened yet.
     * A {@code Match} can be stopped from this state only.
     */
    SETUP,

    /**
     * Indicates that a {@link Match} has been started.
     * A {@code Match} can be stopped from this state only.
     */
    IN_PROGRESS,

    /**
     * Indicates that a {@link Match} has finished.
     * A {@code Match} now has a winner.
     */
    FINISHED,

    /**
     * Indicates that a {@link Match} has been prematurely stopped.
     */
    ABORTED

}
