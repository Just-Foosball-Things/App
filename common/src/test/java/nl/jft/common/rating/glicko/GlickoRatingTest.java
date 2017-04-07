package nl.jft.common.rating.glicko;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.util.util.CommonTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Oscar de Leeuw
 */
public class GlickoRatingTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructor_withNegativeDoubleFirstArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(-1d, 350d, 0.06);
    }

    @Test
    public void constructor_withZeroDoubleFirstArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(0d, 350d, 0.06);
    }

    @Test
    public void constructor_withPositiveDoubleFirstArgument_noException() throws Exception {
        GlickoRating glickoRating = new GlickoRating(150d, 350d, 0.06);

        assertNotNull(glickoRating);
    }

    @Test
    public void constructor_withNegativeDoubleSecondArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(1500d, -350d, 0.06);
    }

    @Test
    public void constructor_withZeroDoubleSecondArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(1500d, 0d, 0.06);
    }

    @Test
    public void constructor_withNegativeDoubleThirdArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(1500d, 350d, -0.06);
    }

    @Test
    public void constructor_withZeroDoubleThirdArgument_expectsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        new GlickoRating(1500d, 350d, 0d);
    }

    @Test
    public void getRating_withDefault_returnsDouble() throws Exception {
        GlickoRating glickoRating = CommonTestUtil.getDefaultRating(); //GlickoRating = 1500

        double expected = 1500d;
        double actual = glickoRating.getValue();

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void getDeviation_withDefault_returnsDouble() throws Exception {
        GlickoRating glickoRating = CommonTestUtil.getDefaultRating(); //Deviation = 350

        double expected = 350d;
        double actual = glickoRating.getDeviation();

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void getVolatility_withDefault_returnsDouble() throws Exception {
        GlickoRating glickoRating = CommonTestUtil.getDefaultRating(); //Volatility = 0.06

        double expected = 0.06d;
        double actual = glickoRating.getVolatility();

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void hashcode_sameObjects_areEqual() throws Exception {
        int expected = CommonTestUtil.getDefaultRating().hashCode();
        int actual = CommonTestUtil.getDefaultRating().hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public void hashcode_otherObjects_areNotEqual() throws Exception {
        int expected = CommonTestUtil.getDefaultRating().hashCode();
        int actual = "string".hashCode();

        assertNotEquals(expected, actual);
    }

    @Test
    public void hashcode_nullObjects_areNotEqual() throws Exception {
        int expected = CommonTestUtil.getDefaultRating().hashCode();
        int actual = 0;

        assertNotEquals(expected, actual);
    }

    @Test
    public void equals_sameObjects_returnsTrue() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = CommonTestUtil.getDefaultRating();

        boolean result = expected.equals(actual);
        assertTrue(result);
    }

    @Test
    public void equals_nullObjects_returnsFalse() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = null;

        boolean result = expected.equals(actual);
        assertFalse(result);
    }

    @Test
    public void equals_otherObjects_returnsFalse() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        String actual = "string";

        boolean result = expected.equals(actual);
        assertFalse(result);
    }

    @Test
    public void equals_differentDeviation_returnsFalse() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = makeRating(1500, 200, 0.06);

        boolean result = expected.equals(actual);
        assertFalse(result);
    }

    @Test
    public void equals_differentVolatility_returnsFalse() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = makeRating(1500, 350, 0.07);

        boolean result = expected.equals(actual);
        assertFalse(result);
    }

    @Test
    public void equals_differentRating_returnsFalse() throws Exception {
        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = makeRating(1400, 350, 0.06);

        boolean result = expected.equals(actual);
        assertFalse(result);
    }

    private GlickoRating makeRating(double rating, double deviation, double volatility) {
        return new GlickoRating(rating, deviation, volatility);
    }
}
