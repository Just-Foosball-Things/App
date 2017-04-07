package nl.jft.logic.tournament;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.ParticipantType;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class TournamentTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullTournamentType_throwsException() {
        expectedException.expect(NullPointerException.class);

        Tournament tournament = ObjectBuilder.tournament().withTournamentType(null).build();
    }

    @Test
    public void construct_nullParticipantType_throwsException() {
        expectedException.expect(NullPointerException.class);

        Tournament tournament = ObjectBuilder.tournament().withParticipantType(null).build();
    }

    @Test
    public void start_whenCalled_startsTournament() {
        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.start();

        TournamentStatus expected = TournamentStatus.IN_PROGRESS;
        TournamentStatus actual = tournament.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void start_tournamentInProgress_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.start();
        tournament.start();
    }

    @Test
    public void start_tournamentFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.start();
        tournament.stop();
        tournament.start();
    }

    @Test
    public void stop_whenCalled_stopsTournament() {
        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.start();
        tournament.stop();

        TournamentStatus expected = TournamentStatus.FINISHED;
        TournamentStatus actual = tournament.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void stop_tournamentSetup_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.stop();
    }

    @Test
    public void stop_tournamentFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.start();
        tournament.stop();
        tournament.stop();
    }

    @Test
    public void addParticipant_nullParticipant_throwsException() {
        expectedException.expect(NullPointerException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.addParticipant(null);
    }

    @Test
    public void addParticipant_userOnTeamTournament_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.TEAM).build();
        Participant participant = ObjectBuilder.user().build();

        tournament.addParticipant(participant);
    }

    @Test
    public void addParticipant_teamOnSoloTournament_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.SOLO).build();
        Participant participant = ObjectBuilder.team().build();

        tournament.addParticipant(participant);
    }

    @Test
    public void addParticipant_tournamentInProgress_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.start();
        tournament.addParticipant(participant);
    }

    @Test
    public void addParticipant_tournamentFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.start();
        tournament.stop();
        tournament.addParticipant(participant);
    }

    @Test
    public void addParticipant_userOnSoloTournament_addsParticipant() {
        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.addParticipant(participant);

        List<Participant> expected = Arrays.asList((Participant) ObjectBuilder.user().build());
        List<Participant> actual = tournament.getParticipants();

        assertEquals(expected, actual);
    }

    @Test
    public void addParticipant_teamOnTeamTournament_addsParticipant() {
        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.TEAM).build();
        Participant participant = ObjectBuilder.team().build();

        tournament.addParticipant(participant);

        List<Participant> expected = Arrays.asList((Participant) ObjectBuilder.team().build());
        List<Participant> actual = tournament.getParticipants();

        assertEquals(expected, actual);
    }

    @Test
    public void removeParticipant_nullParticipant_throwsException() {
        expectedException.expect(NullPointerException.class);


        Tournament tournament = ObjectBuilder.tournament().build();
        tournament.removeParticipant(null);
    }

    @Test
    public void removeParticipant_userOnTeamTournament_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.TEAM).build();
        Participant participant = ObjectBuilder.user().build();

        tournament.removeParticipant(participant);
    }

    @Test
    public void removeParticipant_teamOnSoloTournament_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.SOLO).build();
        Participant participant = ObjectBuilder.team().build();

        tournament.removeParticipant(participant);
    }

    @Test
    public void removeParticipant_tournamentInProgress_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.start();
        tournament.removeParticipant(participant);
    }

    @Test
    public void removeParticipant_tournamentFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.start();
        tournament.stop();
        tournament.removeParticipant(participant);
    }

    @Test
    public void removeParticipant_whenCalled_throwsException() {
        Tournament tournament = ObjectBuilder.tournament().build();
        Participant participant = ObjectBuilder.user().build();

        tournament.addParticipant(participant);
        tournament.removeParticipant(participant);

        List<Participant> expected = new ArrayList<>();
        List<Participant> actual = tournament.getParticipants();

        assertEquals(expected, actual);
    }

    @Test
    public void getWinner_byDefault_returnsSetup() {
        Tournament tournament = ObjectBuilder.tournament().build();

        TournamentStatus expected = TournamentStatus.SETUP;
        TournamentStatus actual = tournament.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void getWinner_byDefault_isEmpty() {
        Tournament tournament = ObjectBuilder.tournament().build();

        Optional<Participant> expected = Optional.empty();
        Optional<Participant> actual = tournament.getWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipants_byDefault_returnsEmptyList() {
        Tournament tournament = ObjectBuilder.tournament().build();

        List<Participant> expected = new ArrayList<>();
        List<Participant> actual = tournament.getParticipants();

        assertEquals(expected, actual);
    }

    @Test
    public void getTournamentType_whenCalled_returnsTournamentType() {
        Tournament tournament = ObjectBuilder.tournament().withTournamentType(TournamentType.KNOCKOUT).build();

        TournamentType expected = TournamentType.KNOCKOUT;
        TournamentType actual = tournament.getTournamentType();

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipantType_whenCalled_returnsParticipantType() {
        Tournament tournament = ObjectBuilder.tournament().withParticipantType(ParticipantType.SOLO).build();

        ParticipantType expected = ParticipantType.SOLO;
        ParticipantType actual = tournament.getParticipantType();

        assertEquals(expected, actual);
    }

}
