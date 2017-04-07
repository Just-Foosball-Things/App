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
public class MessageHandlerChainTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullType_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChain<Message> chain = new MessageHandlerChain<>(null);
    }

    @Test
    public void getType_whenCalled_returnsType() {
        MessageHandlerChain<Message> chain = makeChain(Message.class);

        Class<Message> expected = Message.class;
        Class<Message> actual = chain.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void addMessageHandler_nullHandler_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChain<Message> chain = makeDefaultChain();
        chain.addMessageHandler(null);
    }

    @Test
    public void removeMessageHandler_nullHandler_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChain<Message> chain = makeDefaultChain();
        chain.removeMessageHandler(null);
    }

    @Test
    public void notify_nullMessage_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChain<Message> chain = makeDefaultChain();
        Connection stubConnection = mock(Connection.class);

        chain.notify(stubConnection, null);
    }

    @Test
    public void notify_nullConnection_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageHandlerChain<Message> chain = makeDefaultChain();
        Message stubMessage = new FakeMessage();

        chain.notify(null, stubMessage);
    }

    @Test
    public void notify_removedHandler_doesNotNotify() {
        MessageHandlerChain<FakeMessage> chain = makeChain(FakeMessage.class);
        Connection stubConnection = mock(Connection.class);
        FakeMessage stubMessage = new FakeMessage();
        FakeMessageHandler mockMessageHandler = new FakeMessageHandler();

        chain.addMessageHandler(mockMessageHandler);
        chain.removeMessageHandler(mockMessageHandler);
        chain.notify(stubConnection, stubMessage);

        Message expected = null;
        Message actual = mockMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = null;
        Connection actualConnection = mockMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    @Test
    public void notify_whenCalled_notifiesHandlers() {
        MessageHandlerChain<FakeMessage> chain = makeChain(FakeMessage.class);
        Connection stubConnection = mock(Connection.class);
        FakeMessage stubMessage = new FakeMessage();
        FakeMessageHandler mockMessageHandler = new FakeMessageHandler();

        chain.addMessageHandler(mockMessageHandler);
        chain.notify(stubConnection, stubMessage);

        Message expected = stubMessage;
        Message actual = mockMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = stubConnection;
        Connection actualConnection = mockMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    @Test
    public void notify_whenCalledWithTerminatedMessage_notifiesOneHandler() {
        MessageHandlerChain<FakeMessage> chain = makeChain(FakeMessage.class);
        Connection stubConnection = mock(Connection.class);
        FakeMessage stubMessage = new FakeMessage();

        FakeMessageHandler mockMessageHandler = new FakeMessageHandler();
        mockMessageHandler.terminateMessageAfter = 1;

        FakeMessageHandler stubMessageHandler = new FakeMessageHandler();

        chain.addMessageHandler(mockMessageHandler);
        chain.addMessageHandler(stubMessageHandler);
        chain.notify(stubConnection, stubMessage);

        Message expected = stubMessage;
        Message actual = mockMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = stubConnection;
        Connection actualConnection = mockMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);

        expected = null;
        actual = stubMessageHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        expectedConnection = null;
        actualConnection = stubMessageHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    private MessageHandlerChain<Message> makeDefaultChain() {
        return makeChain(Message.class);
    }

    private <M extends Message> MessageHandlerChain<M> makeChain(Class<M> type) {
        return new MessageHandlerChain<>(type);
    }

}
