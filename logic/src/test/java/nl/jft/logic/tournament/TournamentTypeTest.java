package nl.jft.logic.tournament;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class TournamentTypeTest {

    @Test
    public void knockout_whenCalled_returnsKnockout() {
        TournamentType expected = TournamentType.KNOCKOUT;
        TournamentType actual = TournamentType.valueOf("KNOCKOUT");

        assertEquals(expected, actual);
    }

}