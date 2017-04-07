package nl.jft.logic.match;

/**
 * A {@code MatchType} defines how a {@link Match} should be treated.
 *
 * @author Lesley
 */
public enum MatchType {

    /**
     * A {@code RATED} {@code MatchType} indicates that a {@link nl.jft.logic.participant.Participant}
     * should change its {@link nl.jft.logic.participant.Elo} after finishing a {@link Match}.
     */
    RATED,

    /**
     * A {@code FRIENDLY} {@code MatchType} indicates that a {@link nl.jft.logic.participant.Participant}
     * should not change its {@link nl.jft.logic.participant.Elo} after finishing a {@link Match}.
     */
    FRIENDLY

}
