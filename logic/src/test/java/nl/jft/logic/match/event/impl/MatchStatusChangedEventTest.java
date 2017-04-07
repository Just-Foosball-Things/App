package nl.jft.logic.match.event.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchStatus;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class MatchStatusChangedEventTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullMatch_throwsException() {
        expectedException.expect(NullPointerException.class);

        MatchStatusChangedEvent event = new MatchStatusChangedEvent(null, MatchStatus.SETUP, MatchStatus.IN_PROGRESS);
    }

    @Test
    public void construct_nullOldStatus_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(match, null, MatchStatus.IN_PROGRESS);
    }

    @Test
    public void construct_nullNewStatus_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(match, MatchStatus.SETUP, null);
    }

    @Test
    public void getMatch_whenCalled_returnsMatch() {
        Match match = ObjectBuilder.match().build();
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(match, MatchStatus.SETUP, MatchStatus.IN_PROGRESS);

        Match expected = ObjectBuilder.match().build();
        Match actual = event.getMatch();

        assertEquals(expected, actual);
    }

    @Test
    public void getOldStatus_whenCalled_returnsMatch() {
        Match match = ObjectBuilder.match().build();
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(match, MatchStatus.SETUP, MatchStatus.IN_PROGRESS);

        MatchStatus expected = MatchStatus.SETUP;
        MatchStatus actual = event.getOldStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void getNewStatus_whenCalled_returnsMatch() {
        Match match = ObjectBuilder.match().build();
        MatchStatusChangedEvent event = new MatchStatusChangedEvent(match, MatchStatus.SETUP, MatchStatus.IN_PROGRESS);

        MatchStatus expected = MatchStatus.IN_PROGRESS;
        MatchStatus actual = event.getNewStatus();

        assertEquals(expected, actual);
    }

}
