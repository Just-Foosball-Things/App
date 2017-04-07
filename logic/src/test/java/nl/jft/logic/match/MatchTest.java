package nl.jft.logic.match;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.jft.logic.match.event.MatchListener;
import nl.jft.logic.match.event.impl.GoalRemovedEvent;
import nl.jft.logic.match.event.impl.GoalScoredEvent;
import nl.jft.logic.match.event.impl.MatchStatusChangedEvent;
import nl.jft.logic.participant.Participant;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Lesley
 */
public class MatchTest {

    @org.junit.Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullFirstParticipant_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().withFirstParticipant(null).build();
    }

    @Test
    public void construct_nullSecondParticipant_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().withSecondParticipant(null).build();
    }

    @Test
    public void construct_nullMatchType_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().withMatchType(null).build();
    }

    @Test
    public void addListener_nullListener_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.addListener(null);
    }

    @Test
    public void addListener_whenCalled_addsListener() {
        Match match = ObjectBuilder.match().build();
        MatchListener stubListener = mock(MatchListener.class);

        match.addListener(stubListener);

        List<MatchListener> expected = Arrays.asList(stubListener);
        List<MatchListener> actual = match.getListeners();

        assertEquals(expected, actual);
    }

    @Test
    public void removeListener_nullListener_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.removeListener(null);
    }


    @Test
    public void removeListener_whenCalled_removesListener() {
        Match match = ObjectBuilder.match().build();
        MatchListener stubListener = mock(MatchListener.class);

        match.addListener(stubListener);
        match.removeListener(stubListener);

        List<MatchListener> expected = new ArrayList<>();
        List<MatchListener> actual = match.getListeners();

        assertEquals(expected, actual);
    }

    @Test
    public void start_whenCalled_startsMatch() {
        Match match = ObjectBuilder.match().build();
        match.start();

        MatchStatus expected = MatchStatus.IN_PROGRESS;
        MatchStatus actual = match.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void start_whenCalled_callsListener() {
        Match match = ObjectBuilder.match().build();
        MatchListener mockListener = mock(MatchListener.class);

        match.addListener(mockListener);
        match.start();

        ArgumentCaptor<MatchStatusChangedEvent> argument = ArgumentCaptor.forClass(MatchStatusChangedEvent.class);
        verify(mockListener).onMatchStatusChanged(argument.capture());

        MatchStatusChangedEvent actual = argument.getValue();
        assertEquals(match, actual.getMatch());
        assertEquals(MatchStatus.IN_PROGRESS, actual.getNewStatus());
        assertEquals(MatchStatus.SETUP, actual.getOldStatus());
    }

    @Test
    public void stop_whenCalled_callsListener() {
        Match match = ObjectBuilder.match().build();
        MatchListener mockListener = mock(MatchListener.class);

        match.addListener(mockListener);
        match.start();
        match.stop();

        ArgumentCaptor<MatchStatusChangedEvent> argument = ArgumentCaptor.forClass(MatchStatusChangedEvent.class);
        verify(mockListener, times(2)).onMatchStatusChanged(argument.capture());

        MatchStatusChangedEvent actual = argument.getValue();
        assertEquals(match, actual.getMatch());
        assertEquals(MatchStatus.FINISHED, actual.getNewStatus());
        assertEquals(MatchStatus.IN_PROGRESS, actual.getOldStatus());
    }

    @Test
    public void start_matchInProgress_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Match match = ObjectBuilder.match().build();
        match.start();
        match.start();
    }

    @Test
    public void start_matchFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Match match = ObjectBuilder.match().build();
        match.start();
        match.stop();
        match.start();
    }

    @Test
    public void stop_whenCalled_stopsMatch() {
        Match match = ObjectBuilder.match().build();
        match.start();
        match.stop();

        MatchStatus expected = MatchStatus.FINISHED;
        MatchStatus actual = match.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void stop_matchSetup_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Match match = ObjectBuilder.match().build();
        match.stop();
    }

    @Test
    public void stop_matchFinished_throwsException() {
        expectedException.expect(IllegalStateException.class);

        Match match = ObjectBuilder.match().build();
        match.start();
        match.stop();
        match.stop();
    }

    @Test
    public void addGoal_nullGoal_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.addGoal(null);
    }

    @Test
    public void addGoal_whenCalled_addsGoal() {
        Match match = ObjectBuilder.match().build();
        final Goal goal = ObjectBuilder.goal().build();

        match.addGoal(goal);

        List<Goal> expected = new ArrayList<Goal>() {{
            add(goal);
        }};
        List<Goal> actual = match.getGoals();

        assertEquals(expected, actual);
    }

    @Test
    public void addGoal_whenCalled_callsListener() {
        Match match = ObjectBuilder.match().build();
        Goal goal = ObjectBuilder.goal().build();
        MatchListener mockListener = mock(MatchListener.class);

        match.addListener(mockListener);
        match.addGoal(goal);

        ArgumentCaptor<GoalScoredEvent> argument = ArgumentCaptor.forClass(GoalScoredEvent.class);
        verify(mockListener).onGoalScored(argument.capture());

        GoalScoredEvent actual = argument.getValue();
        assertEquals(match, actual.getMatch());
        assertEquals(goal, actual.getGoal());
    }

    @Test
    public void removeGoal_nullGoal_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.removeGoal(null);
    }

    @Test
    public void removeGoal_whenCalled_removesGoal() {
        Match match = ObjectBuilder.match().build();
        Goal goal = ObjectBuilder.goal().build();

        match.addGoal(goal);
        match.removeGoal(goal);

        List<Goal> expected = new ArrayList<>();
        List<Goal> actual = match.getGoals();

        assertEquals(expected, actual);
    }

    @Test
    public void removeGoal_whenCalled_callsListener() {
        Match match = ObjectBuilder.match().build();
        Goal goal = ObjectBuilder.goal().build();
        MatchListener mockListener = mock(MatchListener.class);

        match.addListener(mockListener);
        match.addGoal(goal);
        match.removeGoal(goal);

        ArgumentCaptor<GoalRemovedEvent> argument = ArgumentCaptor.forClass(GoalRemovedEvent.class);
        verify(mockListener).onGoalRemoved(argument.capture());

        GoalRemovedEvent actual = argument.getValue();
        assertEquals(match, actual.getMatch());
        assertEquals(goal, actual.getGoal());
    }

    @Test
    public void addRule_nullRule_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.addRule(null);
    }

    @Test
    public void addRule_whenCalled_addsRule() {
        Match match = ObjectBuilder.match().build();
        final Rule rule = ObjectBuilder.rule().build();

        match.addRule(rule);

        List<Rule> expected = new ArrayList<Rule>() {{
            add(rule);
        }};
        List<Rule> actual = match.getRules();

        assertEquals(expected, actual);
    }

    @Test
    public void removeRule_nullRule_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        match.removeRule(null);
    }

    @Test
    public void removeRule_whenCalled_removesRule() {
        Match match = ObjectBuilder.match().build();
        Rule rule = ObjectBuilder.rule().build();

        match.addRule(rule);
        match.removeRule(rule);

        List<Rule> expected = new ArrayList<>();
        List<Rule> actual = match.getRules();

        assertEquals(expected, actual);
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Match match1 = ObjectBuilder.match().build();
        Match match2 = ObjectBuilder.match().build();

        boolean result = match1.equals(match2);
        assertTrue(result);
    }

    @Test
    public void equals_otherMatch_returnsFalse() throws Exception {
        Participant participant1 = ObjectBuilder.user().withUsername("user1").build();
        Participant participant2 = ObjectBuilder.user().withUsername("user2").build();
        Participant participant3 = ObjectBuilder.user().withUsername("user3").build();

        Match match1 = ObjectBuilder.match().withFirstParticipant(participant1).withSecondParticipant(participant2).build();
        Match match2 = ObjectBuilder.match().withFirstParticipant(participant2).withSecondParticipant(participant3).build();

        boolean result = match1.equals(match2);
        assertFalse(result);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Match match1 = ObjectBuilder.match().build();
        Match match2 = null;

        boolean result = match1.equals(match2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        Match match1 = ObjectBuilder.match().build();
        Match match2 = ObjectBuilder.match().build();

        boolean result = match1.hashCode() == match2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getStatus_byDefault_returnsSetup() {
        Match match = ObjectBuilder.match().build();

        MatchStatus expected = MatchStatus.SETUP;
        MatchStatus actual = match.getStatus();

        assertEquals(expected, actual);
    }

    @Test
    public void getType_whenCalled_returnsType() {
        Match match = ObjectBuilder.match().withMatchType(MatchType.RATED).build();

        MatchType expected = MatchType.RATED;
        MatchType actual = match.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void getFirstParticipant_whenCalled_returnsFirstParticipant() {
        Match match = ObjectBuilder.match().withFirstParticipant(ObjectBuilder.user().build()).build();

        Participant expected = ObjectBuilder.user().build();
        Participant actual = match.getFirstParticipant();

        assertEquals(expected, actual);
    }

    @Test
    public void getSecondParticipant_whenCalled_returnsSecondParticipant() {
        Match match = ObjectBuilder.match().withSecondParticipant(ObjectBuilder.user().build()).build();

        Participant expected = ObjectBuilder.user().build();
        Participant actual = match.getSecondParticipant();

        assertEquals(expected, actual);
    }

}
