package nl.jft.logic.participant.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.rating.Rating;
import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Title;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public class UserTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullUsername_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withUsername(null).build();
    }

    @Test
    public void construct_emptyUsername_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        User user = ObjectBuilder.user().withUsername("").build();
    }

    @Test
    public void construct_nullRating_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withRating(null).build();
    }

    @Test
    public void construct_nullTitle_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withTitle(null).build();
    }


    @Test
    public void construct2_nullUsername_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withUsername(null).build2(); // Second constructor
    }

    @Test
    public void construct2_emptyUsername_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        User user = ObjectBuilder.user().withUsername("").build2();
    }

    @Test
    public void construct2_nullRating_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withRating(null).build2();
    }

    @Test
    public void construct2_nullTitle_throwsException() {
        expectedException.expect(NullPointerException.class);

        User user = ObjectBuilder.user().withTitle(null).build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        User user1 = ObjectBuilder.user().build2();
        User user2 = ObjectBuilder.user().build2();

        boolean result = user1.equals(user2);
        assertTrue(result);
    }

    @Test
    public void equals_otherUser_returnsFalse() throws Exception {
        User user1 = ObjectBuilder.user().withUsername("user1").build();
        User user2 = ObjectBuilder.user().withUsername("user2").build();

        boolean result = user1.equals(user2);
        assertFalse(result);
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        User user1 = ObjectBuilder.user().build();
        User user2 = null;

        boolean result = user1.equals(user2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        User user1 = ObjectBuilder.user().build();
        User user2 = ObjectBuilder.user().build();

        boolean result = user1.hashCode() == user2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getId_whenCalled_returnsId() throws Exception {
        User user = ObjectBuilder.user().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = user.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getUsername_whenCalled_returnsUsername() {
        User user = ObjectBuilder.user().withUsername("username").build();

        String expected = "username";
        String actual = user.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void getRating_whenCalled_returnsRating() {
        User user = ObjectBuilder.user().withRating(ObjectBuilder.Rating.glickoRating().build()).build();

        Rating expected = ObjectBuilder.Rating.glickoRating().build();
        Rating actual = user.getRating();

        assertEquals(expected, actual);
    }

    @Test
    public void getTitle_whenCalled_returnsTitle() {
        User user = ObjectBuilder.user().withTitle(ObjectBuilder.title().build()).build();

        Title expected = ObjectBuilder.title().build();
        Title actual = user.getActiveTitle();

        assertEquals(expected, actual);
    }
}
