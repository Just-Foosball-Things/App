package nl.jft.logic.util.builder;

import nl.jft.logic.participant.ParticipantType;
import nl.jft.logic.tournament.Tournament;
import nl.jft.logic.tournament.TournamentType;

/**
 * @author Lesley
 */
public final class TournamentBuilder {

    private TournamentType tournamentType;
    private ParticipantType participantType;

    public TournamentBuilder() {
        tournamentType = TournamentType.KNOCKOUT;
        participantType = ParticipantType.SOLO;
    }

    public TournamentBuilder withTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
        return this;
    }

    public TournamentBuilder withParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
        return this;
    }

    public Tournament build() {
        return new Tournament(tournamentType, participantType);
    }

}
