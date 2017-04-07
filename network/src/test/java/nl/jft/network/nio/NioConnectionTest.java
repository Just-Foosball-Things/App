package nl.jft.network.nio;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import io.netty.channel.Channel;
import nl.jft.network.ConnectionTest;
import nl.jft.network.util.mocks.FakeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Lesley
 */
public class NioConnectionTest extends ConnectionTest<NioConnection> {

    private Channel makeChannel() {
        return mock(Channel.class);
    }

    @Override
    public NioConnection makeConnection() {
        return new NioConnection(makeChannel());
    }

    @Test
    public void construct_nullChannel_throwsException() {
        expectedException.expect(NullPointerException.class);

        NioConnection connection = new NioConnection(null);
    }

    @Test
    public void write_whenCalled_writesMessageToChannel() {
        Channel mockChannel = makeChannel();
        FakeMessage stubMessage = new FakeMessage();
        NioConnection connection = new NioConnection(mockChannel);

        connection.write(stubMessage);

        verify(mockChannel).writeAndFlush(ArgumentMatchers.eq(stubMessage));
    }

    @Test
    public void close_whenCalled_closesChannel() {
        Channel mockChannel = makeChannel();
        NioConnection connection = new NioConnection(mockChannel);

        connection.close();

        verify(mockChannel).close();
    }

    @Test
    public void getLocalAddress_whenCalled_returnsChannelLocalAddress() {
        SocketAddress stubAddress = new InetSocketAddress("localhost", 1001);
        Channel mockChannel = makeChannel();
        when(mockChannel.localAddress()).thenReturn(stubAddress);

        NioConnection connection = new NioConnection(mockChannel);

        SocketAddress expected = stubAddress;
        SocketAddress actual = connection.getLocalAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void getRemoteAddress_whenCalled_returnsChannelRemoteAddress() {
        SocketAddress stubAddress = new InetSocketAddress("localhost", 1001);
        Channel mockChannel = makeChannel();
        when(mockChannel.remoteAddress()).thenReturn(stubAddress);

        NioConnection connection = new NioConnection(mockChannel);

        SocketAddress expected = stubAddress;
        SocketAddress actual = connection.getRemoteAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void isActive_whenCalled_returnsChannelIsActive() {
        Channel mockChannel = makeChannel();
        when(mockChannel.isActive()).thenReturn(true);

        NioConnection connection = new NioConnection(mockChannel);

        assertTrue(connection.isActive());
    }

}
