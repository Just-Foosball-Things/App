package nl.jft.logic.tournament;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class TournamentStatusTest {

    @Test
    public void setup_whenCalled_returnsSetup() {
        TournamentStatus expected = TournamentStatus.SETUP;
        TournamentStatus actual = TournamentStatus.valueOf("SETUP");

        assertEquals(expected, actual);
    }

    @Test
    public void inProgress_whenCalled_returnsInProgress() {
        TournamentStatus expected = TournamentStatus.IN_PROGRESS;
        TournamentStatus actual = TournamentStatus.valueOf("IN_PROGRESS");

        assertEquals(expected, actual);
    }

    @Test
    public void finished_whenCalled_returnsFinished() {
        TournamentStatus expected = TournamentStatus.FINISHED;
        TournamentStatus actual = TournamentStatus.valueOf("FINISHED");

        assertEquals(expected, actual);
    }

    @Test
    public void aborted_whenCalled_returnsAborted() {
        TournamentStatus expected = TournamentStatus.ABORTED;
        TournamentStatus actual = TournamentStatus.valueOf("ABORTED");

        assertEquals(expected, actual);
    }

}