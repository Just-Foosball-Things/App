package nl.jft.logic.match;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class MatchStatusTest {

    @Test
    public void setup_whenCalled_returnsSetup() {
        MatchStatus expected = MatchStatus.SETUP;
        MatchStatus actual = MatchStatus.valueOf("SETUP");

        assertEquals(expected, actual);
    }

    @Test
    public void inProgress_whenCalled_returnsInProgress() {
        MatchStatus expected = MatchStatus.IN_PROGRESS;
        MatchStatus actual = MatchStatus.valueOf("IN_PROGRESS");

        assertEquals(expected, actual);
    }

    @Test
    public void finished_whenCalled_returnsFinished() {
        MatchStatus expected = MatchStatus.FINISHED;
        MatchStatus actual = MatchStatus.valueOf("FINISHED");

        assertEquals(expected, actual);
    }

    @Test
    public void aborted_whenCalled_returnsAborted() {
        MatchStatus expected = MatchStatus.ABORTED;
        MatchStatus actual = MatchStatus.valueOf("ABORTED");

        assertEquals(expected, actual);
    }

}