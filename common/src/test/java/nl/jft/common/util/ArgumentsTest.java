package nl.jft.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class ArgumentsTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_whenCalled_throwsException() throws Throwable {
        expectedException.expect(UnsupportedOperationException.class);

        try {
            Constructor<Arguments> constructor = Arguments.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Test
    public void requireNotEmpty_nullString_throwsException() {
        expectedException.expect(NullPointerException.class);

        Arguments.requireNotEmpty(null);
    }

    @Test
    public void requireNotEmpty_emptyString_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Arguments.requireNotEmpty("");
    }

    @Test
    public void requireNotEmpty_stringWithSpacesOnly_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Arguments.requireNotEmpty("    ");
    }

    @Test
    public void requireNotEmpty_nonEmptyString_returnsString() {
        String expected = "value";
        String actual = Arguments.requireNotEmpty("value");

        assertEquals(expected, actual);
    }

    @Test
    public void requireNotNegative_negativeDouble_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Arguments.requireNotNegative(-1);
    }

    @Test
    public void requireNotNegative_zeroDouble_returnsDouble() {
        double expected = 0;
        double actual = Arguments.requireNotNegative(0);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void requireNotNegative_positiveDouble_returnsDouble() {
        double expected = 5.5d;
        double actual = Arguments.requireNotNegative(5.5d);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void requirePositive_negativeDouble_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        Arguments.requirePositive(-1d);
    }

    @Test
    public void requirePositive_zeroDouble_returnsDouble() {
        expectedException.expect(IllegalArgumentException.class);

        Arguments.requirePositive(0d);
    }

    @Test
    public void requirePositive_positiveDouble_returnsDouble() {
        double expected = 5.5d;
        double actual = Arguments.requireNotNegative(5.5d);

        assertEquals(expected, actual, 0.00000001d);
    }

    @Test
    public void requireNotSame_sameInstance_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        Object obj = new Object();
        Object other = obj;

        Arguments.requireNotSame(obj, other);
    }

    @Test
    public void requireNotSame_nullObject_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        Object obj = null;
        Object other = new Object();

        Arguments.requireNotSame(obj, other);
    }

    @Test
    public void requireNotSame_nullObjectSecondArgument_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        Object obj = new Object();
        Object other = null;

        Arguments.requireNotSame(obj, other);
    }

    @Test
    public void requireNotSame_differentInstance_returnsObj() throws Exception {
        Object obj = new Object();
        Object other = new Object();

        Object result = Arguments.requireNotSame(obj, other);

        assertEquals(result, obj);
    }

    @Test
    public void requireNotSame_differentInstanceAreEqual_returnsObj() throws Exception {
        String obj = new String("test");
        String other = new String("test");

        String result = Arguments.requireNotSame(obj, other);

        assertEquals(obj, result);
    }
}