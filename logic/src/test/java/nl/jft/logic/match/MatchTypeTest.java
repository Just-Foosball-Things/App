package nl.jft.logic.match;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class MatchTypeTest {

    @Test
    public void rated_whenCalled_returnsRated() {
        MatchType expected = MatchType.RATED;
        MatchType actual = MatchType.valueOf("RATED");

        assertEquals(expected, actual);
    }

    @Test
    public void friendly_whenCalled_returnsFriendly() {
        MatchType expected = MatchType.FRIENDLY;
        MatchType actual = MatchType.valueOf("FRIENDLY");

        assertEquals(expected, actual);
    }

}