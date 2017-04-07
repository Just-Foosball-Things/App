package nl.jft.logic.participant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lesley
 */
public class TitleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullName_throwsException() {
        expectedException.expect(NullPointerException.class);

        Title title = ObjectBuilder.title().withName(null).build();
    }

    @Test
    public void construct_emptyName_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Title title = ObjectBuilder.title().withName("").build();
    }

    @Test
    public void construct2_nullName_throwsException() {
        expectedException.expect(NullPointerException.class);

        Title title = ObjectBuilder.title().withName(null).build2();
    }

    @Test
    public void construct2_emptyName_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Title title = ObjectBuilder.title().withName("").build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Title title1 = ObjectBuilder.title().build2();
        Title title2 = ObjectBuilder.title().build2();

        boolean result = title1.equals(title2);
        assertTrue(result);
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        Title title1 = ObjectBuilder.title().build();
        Title title2 = null;

        boolean result = title1.equals(title2);
        assertFalse(result);
    }

    @Test
    public void equals_differentIds_returnsFalse() {
        Title title1 = ObjectBuilder.title().withId(1).build();
        Title title2 = ObjectBuilder.title().withId(2).build();

        boolean result = title1.equals(title2);
        assertFalse(result);
    }

    @Test
    public void equals_differentNames_returnsFalse() {
        Title title1 = ObjectBuilder.title().withName("title1").build();
        Title title2 = ObjectBuilder.title().withName("title2").build();

        boolean result = title1.equals(title2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        Title title1 = ObjectBuilder.title().build();
        Title title2 = ObjectBuilder.title().build();

        boolean result = title1.hashCode() == title2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getId_whenCalled_returnsId() {
        Title title = ObjectBuilder.title().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = title.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getName_whenCalled_returnsName() {
        Title title = ObjectBuilder.title().withName("name").build();

        String expected = "name";
        String actual = title.getName();

        assertEquals(expected, actual);
    }

}
