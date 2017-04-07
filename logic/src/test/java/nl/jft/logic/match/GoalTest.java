package nl.jft.logic.match;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public class GoalTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullTime_throwsException() {
        expectedException.expect(NullPointerException.class);

        Goal goal = ObjectBuilder.goal().withTime(null).build();
    }

    @Test
    public void construct2_nullTime_throwsException() {
        expectedException.expect(NullPointerException.class);

        Goal goal = ObjectBuilder.goal().withTime(null).build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Goal goal1 = ObjectBuilder.goal().build2();
        Goal goal2 = ObjectBuilder.goal().build2();

        boolean result = goal1.equals(goal2);
        assertTrue(result);
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        Goal goal1 = ObjectBuilder.goal().build();
        Goal goal2 = null;

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void equals_nullFirstParticipant_returnsFalse() {
        Goal goal1 = ObjectBuilder.goal().withParticipant(null).build();
        Goal goal2 = ObjectBuilder.goal().withParticipant(ObjectBuilder.user().build()).build();

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void equals_nullSecondParticipant_returnsFalse() {
        Goal goal1 = ObjectBuilder.goal().withParticipant(ObjectBuilder.user().build()).build();
        Goal goal2 = ObjectBuilder.goal().withParticipant(null).build();

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void equals_nullBothParticipants_returnsTrue() {
        Goal goal1 = ObjectBuilder.goal().withParticipant(null).build();
        Goal goal2 = ObjectBuilder.goal().withParticipant(null).build();

        boolean result = goal1.equals(goal2);
        assertTrue(result);
    }

    @Test
    public void equals_differentIds_returnsFalse() {
        Goal goal1 = ObjectBuilder.goal().withId(1).build();
        Goal goal2 = ObjectBuilder.goal().withId(2).build();

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void equals_differentParticipants_returnsFalse() {
        Participant participant1 = ObjectBuilder.user().withUsername("user1").build();
        Participant participant2 = ObjectBuilder.user().withUsername("user2").build();

        Goal goal1 = ObjectBuilder.goal().withParticipant(participant1).build();
        Goal goal2 = ObjectBuilder.goal().withParticipant(participant2).build();

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void equals_differentTimes_returnsFalse() {
        Goal goal1 = ObjectBuilder.goal().withTime(new Date(Long.MIN_VALUE)).build();
        Goal goal2 = ObjectBuilder.goal().withTime(new Date(Long.MAX_VALUE)).build();

        boolean result = goal1.equals(goal2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        Goal goal1 = ObjectBuilder.goal().build();
        Goal goal2 = ObjectBuilder.goal().build();

        boolean result = goal1.hashCode() == goal2.hashCode();
        assertTrue(result);
    }

    @Test
    public void hashCode_nullParticipant_returnsHashCode() {
        Goal goal1 = ObjectBuilder.goal().withParticipant(null).build();
        Goal goal2 = ObjectBuilder.goal().withParticipant(null).build();

        boolean result = goal1.hashCode() == goal2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getId_whenCalled_returnsId() {
        Goal goal = ObjectBuilder.goal().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = goal.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipant_nullParticipant_returnsNull() {
        Goal goal = ObjectBuilder.goal().withParticipant(null).build();

        Participant expected = null;
        Participant actual = goal.getParticipant();

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipant_userParticipant_returnsUser() {
        Goal goal = ObjectBuilder.goal().withParticipant(ObjectBuilder.user().build()).build();

        Participant expected = ObjectBuilder.user().build();
        Participant actual = goal.getParticipant();

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipant_teamParticipant_returnsTeam() {
        Goal goal = ObjectBuilder.goal().withParticipant(ObjectBuilder.team().build()).build();

        Participant expected = ObjectBuilder.team().build();
        Participant actual = goal.getParticipant();

        assertEquals(expected, actual);
    }

    @Test
    public void getTime_whenCalled_returnsTime() {
        Goal goal = ObjectBuilder.goal().withTime(new Date(Long.MAX_VALUE)).build();

        Date expected = new Date(Long.MAX_VALUE);
        Date actual = goal.getTime();

        assertEquals(expected, actual);
    }

}
