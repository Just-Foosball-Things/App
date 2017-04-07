package nl.jft.network;

import java.net.InetSocketAddress;

import nl.jft.network.nio.NioClient;
import nl.jft.network.nio.NioServer;

/**
 * @author Lesley
 */
public final class Main {

    private Main() {
        throw new UnsupportedOperationException("Should not be called.");
    }

    public static void main(String[] args) throws Exception {
        Server server = new NioServer();
        server.bind(new InetSocketAddress(3046));

        Client client = new NioClient();
        client.connect(new InetSocketAddress("localhost", 3046));
    }
}
