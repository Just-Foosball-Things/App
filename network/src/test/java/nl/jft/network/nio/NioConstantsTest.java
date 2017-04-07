package nl.jft.network.nio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lesley
 */
public class NioConstantsTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_whenCalled_throwsException() throws Throwable {
        expectedException.expect(UnsupportedOperationException.class);

        try {
            Constructor<NioConstants> constructor = NioConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();

        }
    }

}