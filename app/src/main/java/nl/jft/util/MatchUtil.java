package nl.jft.util;

import java.util.Objects;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;

public final class MatchUtil {

    private MatchUtil() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static boolean isWinner(Match match, Participant participant) {
        return Objects.equals(getWinner(match), participant);
    }

    public static Participant getWinner(Match match) {
        Participant firstParticipant = match.getFirstParticipant();
        Participant secondParticipant = match.getSecondParticipant();

        int firstGoals = getAmountOfGoals(match, firstParticipant);
        int secondGoals = getAmountOfGoals(match, secondParticipant);

        return firstGoals > secondGoals ? firstParticipant : secondParticipant;
    }

    public static int getAmountOfGoals(Match match, Participant participant) {
        int amount = 0;

        for (Goal goal : match.getGoals()) {
            if (Objects.equals(goal.getParticipant(), participant)) {
                amount += 1;
            }
        }

        return amount;
    }

}
