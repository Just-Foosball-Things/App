package nl.jft.logic.match;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public class MatchResultTest {

    @org.junit.Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullMatch_throwsException() {
        expectedException.expect(NullPointerException.class);

        MatchResult result = ObjectBuilder.matchResult().withMatch(null).build();
    }

    @Test
    public void construct2_nullMatch_throwsException() {
        expectedException.expect(NullPointerException.class);

        MatchResult result = ObjectBuilder.matchResult().withMatch(null).build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        MatchResult result1 = ObjectBuilder.matchResult().build2();
        MatchResult result2 = ObjectBuilder.matchResult().build2();

        boolean result = result1.equals(result2);
        assertTrue(result);
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        MatchResult result1 = ObjectBuilder.matchResult().build();
        MatchResult result2 = null;

        boolean result = result1.equals(result2);
        assertFalse(result);
    }

    @Test
    public void equals_differentIds_returnsFalse() {
        MatchResult result1 = ObjectBuilder.matchResult().withId(1).build();
        MatchResult result2 = ObjectBuilder.matchResult().withId(2).build();

        boolean result = result1.equals(result2);
        assertFalse(result);
    }

    @Test
    public void equals_differentMatches_returnsFalse() {
        Participant participant1 = ObjectBuilder.user().withUsername("user1").build();
        Participant participant2 = ObjectBuilder.user().withUsername("user2").build();
        Participant participant3 = ObjectBuilder.user().withUsername("user3").build();

        Match firstMatch = ObjectBuilder.match().withFirstParticipant(participant1).withSecondParticipant(participant2).build();
        Match secondMatch = ObjectBuilder.match().withFirstParticipant(participant1).withSecondParticipant(participant3).build();

        MatchResult result1 = ObjectBuilder.matchResult().withMatch(firstMatch).build();
        MatchResult result2 = ObjectBuilder.matchResult().withMatch(secondMatch).build();

        boolean result = result1.equals(result2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        MatchResult matchResult1 = ObjectBuilder.matchResult().build();
        MatchResult matchResult2 = ObjectBuilder.matchResult().build();

        boolean result = matchResult1.hashCode() == matchResult2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getId_whenCalled_returnsId() {
        MatchResult result = ObjectBuilder.matchResult().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = result.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getMatch_whenCalled_returnsMatch() {
        MatchResult result = ObjectBuilder.matchResult().withMatch(ObjectBuilder.match().build()).build();

        Match expected = ObjectBuilder.match().build();
        Match actual = result.getMatch();

        assertEquals(expected, actual);
    }

}
