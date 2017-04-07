package nl.jft.network.nio;

import org.nustaq.serialization.FSTConfiguration;

import nl.jft.network.ServerTest;
import nl.jft.network.message.MessageHandlerChainSet;

/**
 * @author Lesley
 */
public class NioServerTest extends ServerTest<NioServer> {

    @Override
    public NioServer makeEndPoint() {
        return new NioServer();
    }

    @Override
    public NioServer makeEndPoint(FSTConfiguration serializer, MessageHandlerChainSet chainSet) {
        return new NioServer(serializer, chainSet);
    }
}
