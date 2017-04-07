package nl.jft.logic.util;

import java.util.Objects;

import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.ParticipantType;
import nl.jft.logic.participant.impl.User;

/**
 * This class provides various utility methods regarding {@link Participant participants}.
 *
 * @author Lesley
 */
public final class ParticipantUtil {

    private ParticipantUtil() {
        throw new UnsupportedOperationException("Should not be called.");
    }

    /**
     * Gets the {@code ParticipantType} from a {@link Participant}.
     *
     * @param participant The {@code Participant} to determine the {@code ParticipantType} of, should not be {@code null}.
     * @return The {@code ParticipantType} that identifies the given {@code Participant}.
     * @throws NullPointerException If the given {@code Participant} is {@code null}.
     */
    public static ParticipantType getParticipantType(Participant participant) {
        return Objects.requireNonNull(participant).getClass() == User.class ? ParticipantType.SOLO : ParticipantType.TEAM;
    }

}
