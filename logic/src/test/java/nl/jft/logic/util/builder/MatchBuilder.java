package nl.jft.logic.util.builder;

import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchType;
import nl.jft.logic.participant.Participant;

/**
 * @author Lesley
 */
public final class MatchBuilder {

    private Participant firstParticipant;
    private Participant secondParticipant;
    private MatchType type;

    public MatchBuilder() {
        firstParticipant = ObjectBuilder.user().withUsername("user1").build();
        secondParticipant = ObjectBuilder.user().withUsername("user2").build();
        type = MatchType.RATED;
    }

    public MatchBuilder withFirstParticipant(Participant firstParticipant) {
        this.firstParticipant = firstParticipant;
        return this;
    }

    public MatchBuilder withSecondParticipant(Participant secondParticipant) {
        this.secondParticipant = secondParticipant;
        return this;
    }

    public MatchBuilder withMatchType(MatchType type) {
        this.type = type;
        return this;
    }

    public Match build() {
        return new Match(firstParticipant, secondParticipant, type);
    }

}
