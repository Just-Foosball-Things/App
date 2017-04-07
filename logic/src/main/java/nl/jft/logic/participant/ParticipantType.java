package nl.jft.logic.participant;

/**
 * A {@code ParticipantType} defines what kind of type a {@link Participant} is.
 *
 * @author Lesley
 */
public enum ParticipantType {

    /**
     * Indicates that a {@link Participant} is playing on his/her own. The implementation is, usually,
     * {@link nl.jft.logic.participant.impl.User}.
     */
    SOLO,

    /**
     * Indicates that a {@link Participant} is playing with other {@code Participants}. The implementation is, usually,
     * {@link nl.jft.logic.participant.impl.Team}.
     */
    TEAM

}
