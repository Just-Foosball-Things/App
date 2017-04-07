package nl.jft.logic.participant.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.rating.Rating;
import nl.jft.logic.LogicConstants;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 * @author Oscar de Leeuw
 */
public class TeamTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullTeamName_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withTeamName(null).build();
    }

    @Test
    public void construct_emptyTeamName_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Team team = ObjectBuilder.team().withTeamName("").build();
    }

    @Test
    public void construct_nullRating_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withRating(null).build();
    }

    @Test
    public void construct_nullFirstUser_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withFirstUser(null).build();
    }

    @Test
    public void construct_nullSecondUser_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withSecondUser(null).build();
    }

    @Test
    public void construct_sameUsers_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        User user1 = ObjectBuilder.user().build();
        User user2 = ObjectBuilder.user().build();

        Team team = ObjectBuilder.team().withFirstUser(user1).withSecondUser(user2).build();
    }

    @Test
    public void construct2_nullTeamName_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withTeamName(null).build2();
    }

    @Test
    public void construct2_emptyTeamName_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Team team = ObjectBuilder.team().withTeamName("").build2();
    }

    @Test
    public void construct2_nullRating_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withRating(null).build2();
    }

    @Test
    public void construct2_nullFirstUser_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withFirstUser(null).build2();
    }

    @Test
    public void construct2_nullSecondUser_throwsException() {
        expectedException.expect(NullPointerException.class);

        Team team = ObjectBuilder.team().withSecondUser(null).build2();
    }

    @Test
    public void construct2_sameUsers_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        User user1 = ObjectBuilder.user().build();
        User user2 = ObjectBuilder.user().build();

        Team team = ObjectBuilder.team().withFirstUser(user1).withSecondUser(user2).build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Team team1 = ObjectBuilder.team().build2();
        Team team2 = ObjectBuilder.team().build2();

        boolean result = team1.equals(team2);
        assertTrue(result);
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        Team team1 = ObjectBuilder.team().build();
        Team team2 = null;

        boolean result = team1.equals(team2);
        assertFalse(result);
    }

    @Test
    public void equals_differentIds_returnsFalse() {
        Team team1 = ObjectBuilder.team().withId(1).build();
        Team team2 = ObjectBuilder.team().withId(2).build();

        boolean result = team1.equals(team2);
        assertFalse(result);
    }

    @Test
    public void equals_differentTeamNames_returnsFalse() {
        Team team1 = ObjectBuilder.team().withTeamName("team1").build();
        Team team2 = ObjectBuilder.team().withTeamName("team2").build();

        boolean result = team1.equals(team2);
        assertFalse(result);
    }

    @Test
    public void equals_differentFirstUser_returnsFalse() {
        User user1 = ObjectBuilder.user().withUsername("user1").build();
        User user2 = ObjectBuilder.user().withUsername("user2").build();
        User user3 = ObjectBuilder.user().withUsername("user3").build();

        Team team1 = ObjectBuilder.team().withFirstUser(user1).withSecondUser(user3).build();
        Team team2 = ObjectBuilder.team().withFirstUser(user2).withSecondUser(user3).build();

        boolean result = team1.equals(team2);
        assertFalse(result);
    }

    @Test
    public void equals_differentSecondUser_returnsFalse() {
        User user1 = ObjectBuilder.user().withUsername("user1").build();
        User user2 = ObjectBuilder.user().withUsername("user2").build();
        User user3 = ObjectBuilder.user().withUsername("user3").build();

        Team team1 = ObjectBuilder.team().withFirstUser(user1).withSecondUser(user2).build();
        Team team2 = ObjectBuilder.team().withFirstUser(user1).withSecondUser(user3).build();

        boolean result = team1.equals(team2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        Team team1 = ObjectBuilder.team().build();
        Team team2 = ObjectBuilder.team().build();

        boolean result = team1.hashCode() == team2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getName_withDefaultName_returnsName() {
        Team team = ObjectBuilder.team().withTeamName("name").build();

        String expected = "name";
        String actual = team.getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getId_withDefaultId_returnsId() {
        Team team = ObjectBuilder.team().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = team.getId();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRating_whenCalled_returnsRating() {
        Team team = ObjectBuilder.team().withRating(ObjectBuilder.Rating.glickoRating().build()).build();

        Rating expected = ObjectBuilder.Rating.glickoRating().build();
        Rating actual = team.getRating();

        assertEquals(expected, actual);
    }

}
