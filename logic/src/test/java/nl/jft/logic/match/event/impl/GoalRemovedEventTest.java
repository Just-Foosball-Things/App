package nl.jft.logic.match.event.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class GoalRemovedEventTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullMatch_throwsException() {
        expectedException.expect(NullPointerException.class);

        Goal goal = ObjectBuilder.goal().build();
        GoalRemovedEvent event = new GoalRemovedEvent(null, goal);
    }

    @Test
    public void construct_nullGoal_throwsException() {
        expectedException.expect(NullPointerException.class);

        Match match = ObjectBuilder.match().build();
        GoalRemovedEvent event = new GoalRemovedEvent(match, null);
    }

    @Test
    public void getMatch_whenCalled_returnsGoal() {
        Match match = ObjectBuilder.match().build();
        Goal goal = ObjectBuilder.goal().build();
        GoalRemovedEvent event = new GoalRemovedEvent(match, goal);

        Match expected = ObjectBuilder.match().build();
        Match actual = event.getMatch();

        assertEquals(expected, actual);
    }

    @Test
    public void getGoal_whenCalled_returnsGoal() {
        Match match = ObjectBuilder.match().build();
        Goal goal = ObjectBuilder.goal().build();
        GoalRemovedEvent event = new GoalRemovedEvent(match, goal);

        Goal expected = ObjectBuilder.goal().build();
        Goal actual = event.getGoal();

        assertEquals(expected, actual);
    }

}
