package nl.jft.network.util.mocks;

import nl.jft.network.Connection;
import nl.jft.network.message.MessageHandler;

/**
 * @author Lesley
 */
public class FakeMessageHandler extends MessageHandler<FakeMessage> {

    public FakeMessage lastHandleFakeMessage;
    public Connection lastHandleConnection;

    public int terminateMessageAfter = -1;

    private int currentTerminateCount = 0;

    @Override
    public void handle(Connection connection, FakeMessage message) {
        lastHandleConnection = connection;
        lastHandleFakeMessage = message;

        if (terminateMessageAfter != -1 && ++currentTerminateCount >= terminateMessageAfter) {
            message.terminate();
            currentTerminateCount = 0;
        }
    }

    @Override
    public Class<FakeMessage> getType() {
        return FakeMessage.class;
    }

}
