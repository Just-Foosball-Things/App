package nl.jft.logic.participant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class ParticipantTypeTest {

    @Test
    public void solo_whenCalled_returnsSolo() {
        ParticipantType expected = ParticipantType.SOLO;
        ParticipantType actual = ParticipantType.valueOf("SOLO");

        assertEquals(expected, actual);
    }

    @Test
    public void team_whenCalled_returnsTeam() {
        ParticipantType expected = ParticipantType.TEAM;
        ParticipantType actual = ParticipantType.valueOf("TEAM");

        assertEquals(expected, actual);
    }

}