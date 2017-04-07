package nl.jft.logic.tournament;

/**
 * A {@code TournamentType} defines how {@link nl.jft.logic.match.Match matches} should be schedules
 * in a {@link Tournament}.
 *
 * @author Lesley
 */
public enum TournamentType {

    /**
     * {@code KNOCKOUT} indicates that {@link nl.jft.logic.participant.Participant participants} should be kicked out
     * of a {@link Tournament} after they lose a {@link nl.jft.logic.match.Match}.
     */
    KNOCKOUT

}
