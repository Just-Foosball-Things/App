package nl.jft.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Oscar de Leeuw
 */
public class NumbersTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_whenCalled_throwsException() throws Throwable {
        expectedException.expect(UnsupportedOperationException.class);

        try {
            Constructor<Numbers> constructor = Numbers.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Test
    public void checkEqual_doublesInRangeOfDelta_returnsTrue() throws Exception {
        double x = 5d;
        double y = 5d;
        double delta = 0.01d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesOutOfRangeOfDelta_returnsFalse() throws Exception {
        double x = 5d;
        double y = 6d;
        double delta = 0.01d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertFalse(result);
    }

    @Test
    public void checkEqual_doublesDifferInRangeOfDelta_returnsFalse() throws Exception {
        double x = 5.1d;
        double y = 5.2d;
        double delta = 0.2d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesDifferOutOfRangeOfDelta_returnsFalse() throws Exception {
        double x = 5.1d;
        double y = 5.2d;
        double delta = 0.001d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertFalse(result);
    }

    @Test
    public void checkEqual_doublesAreNaN_returnsTrue() throws Exception {
        double x = Double.NaN;
        double y = Double.NaN;
        double delta = 0.01d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesArePositiveInfinity_returnsTrue() throws Exception {
        double x = Double.POSITIVE_INFINITY;
        double y = Double.POSITIVE_INFINITY;
        double delta = 0.01d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesAreNegativeInfinity_returnsTrue() throws Exception {
        double x = Double.NEGATIVE_INFINITY;
        double y = Double.NEGATIVE_INFINITY;
        double delta = 0.01d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesInRangeOfConstantDelta_returnsTrue() throws Exception {
        double x = 5d;
        double y = 5d;

        boolean result = Numbers.checkEqual(x, y);

        assertTrue(result);
    }

    @Test
    public void checkEqual_doublesOutOfRangeOfConstantDelta_returnsFalse() throws Exception {
        double x = 5d;
        double y = 6d;

        boolean result = Numbers.checkEqual(x, y);

        assertFalse(result);
    }

    @Test
    public void checkEqual_withZeroDelta_returnsTrue() throws Exception {
        double x = 2.5 * 2;
        double y = 10 / 2;
        double delta = 0d;

        boolean result = Numbers.checkEqual(x, y, delta);

        assertTrue(result);
    }
}