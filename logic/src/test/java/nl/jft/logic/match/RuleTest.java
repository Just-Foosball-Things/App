package nl.jft.logic.match;

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
public class RuleTest {

    @org.junit.Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullDescription_throwsException() {
        expectedException.expect(NullPointerException.class);

        Rule rule = ObjectBuilder.rule().withDescription(null).build();
    }

    @Test
    public void construct_emptyDescription_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Rule rule = ObjectBuilder.rule().withDescription("").build();
    }

    @Test
    public void construct2_nullDescription_throwsException() {
        expectedException.expect(NullPointerException.class);

        Rule rule = ObjectBuilder.rule().withDescription(null).build2();
    }

    @Test
    public void construct2_emptyDescription_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Rule rule = ObjectBuilder.rule().withDescription("").build2();
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Rule rule1 = ObjectBuilder.rule().build2();
        Rule rule2 = ObjectBuilder.rule().build2();

        boolean result = rule1.equals(rule2);
        assertTrue(result);
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        Rule rule1 = ObjectBuilder.rule().build();
        Rule rule2 = null;

        boolean result = rule1.equals(rule2);
        assertFalse(result);
    }

    @Test
    public void equals_differentIds_returnsFalse() {
        Rule rule1 = ObjectBuilder.rule().withId(1).build();
        Rule rule2 = ObjectBuilder.rule().withId(2).build();

        boolean result = rule1.equals(rule2);
        assertFalse(result);
    }

    @Test
    public void equals_differentDescriptions_returnsFalse() {
        Rule rule1 = ObjectBuilder.rule().withDescription("desc1").build();
        Rule rule2 = ObjectBuilder.rule().withDescription("desc2").build();

        boolean result = rule1.equals(rule2);
        assertFalse(result);
    }

    @Test
    public void hashCode_whenCalled_returnsHashCode() {
        Rule rule1 = ObjectBuilder.rule().build();
        Rule rule2 = ObjectBuilder.rule().build();

        boolean result = rule1.hashCode() == rule2.hashCode();
        assertTrue(result);
    }

    @Test
    public void getId_whenCalled_returnsId() {
        Rule rule = ObjectBuilder.rule().withId(LogicConstants.INTERNAL_ID).build();

        int expected = LogicConstants.INTERNAL_ID;
        int actual = rule.getId();

        assertEquals(expected, actual);
    }

    @Test
    public void getDescription_whenCalled_returnsDescription() {
        Rule rule = ObjectBuilder.rule().withDescription("desc").build();

        String expected = "desc";
        String actual = rule.getDescription();

        assertEquals(expected, actual);
    }

}
