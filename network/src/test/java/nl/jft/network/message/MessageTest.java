package nl.jft.network.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public abstract class MessageTest<M extends Message> {

    protected abstract M makeMessage();

    @Test
    public final void terminate_whenCalled_terminatesMessage() {
        M message = makeMessage();

        message.terminate();

        boolean expected = true;
        boolean actual = message.isTerminated();

        assertEquals(expected, actual);
    }

}