package nl.jft.widget.util;

import nl.jft.logic.participant.Participant;

public final class ParticipantUtil {

    private ParticipantUtil() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static int getRoundedRating(Participant participant) {
        return (int) Math.round(participant.getRating().getValue());
    }

}
