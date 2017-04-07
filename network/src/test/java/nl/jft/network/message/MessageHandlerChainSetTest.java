package nl.jft.network.message;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.network.Connection;
import nl.jft.network.util.mocks.FakeMessage;
import nl.jft.network.util.mocks.FakeMessageHandler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Lesley
 */
public class MessageHandlerChainSetTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Test
    public void addHandler_nullHandler_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChainSet chainSet = makeChainSet();

        chainSet.addHandler(null);
    }

    @Test
    public void removeHandler_nullHandler_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChainSet chainSet = makeChainSet();

        chainSet.removeHandler(null);
    }

    @Test
    public void notify_nullMessage_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChainSet chainSet = makeChainSet();
        Connection stubConnection = mock(Connection.class);

        chainSet.notify(stubConnection, null);
    }

    @Test
    public void notify_nullConnection_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChainSet chainSet = makeChainSet();
        Message stubMessage = new FakeMessage();

        chainSet.notify(null, stubMessage);
    }

    @Test
    public void notify_whenCalled_notifiesHandler() {
        MessageHandlerChainSet chainSet = makeChainSet();
        Connection stubConnection = mock(Connection.class);

        Message stubMessage = new FakeMessage();
        FakeMessageHandler mockMessageHandler = new FakeMessageHandler();

        chainSet.addHandler(mockMessageHandler);
        chainSet.notify(stubConnection, stubMessage);

        Message expected = stubMessage;
        Message actual = mockMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = stubConnection;
        Connection actualConnection = mockMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    @Test
    public void notify_whenCalledOnRemovedHandler_doesNotNotify() {
        MessageHandlerChainSet chainSet = makeChainSet();
        Connection stubConnection = mock(Connection.class);

        Message stubMessage = new FakeMessage();
        FakeMessageHandler mockMessageHandler = new FakeMessageHandler();

        chainSet.addHandler(mockMessageHandler);
        chainSet.removeHandler(mockMessageHandler);
        chainSet.notify(stubConnection, stubMessage);

        Message expected = null;
        Message actual = mockMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = null;
        Connection actualConnection = mockMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    private MessageHandlerChainSet makeChainSet() {
        return new MessageHandlerChainSet();
    }

}
