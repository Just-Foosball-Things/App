package nl.jft.network.nio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.netty.channel.ChannelHandlerContext;
import nl.jft.network.EndPoint;

import static org.mockito.Mockito.mock;

/**
 * @author Lesley
 */
public class HandshakeFutureListenerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullEndPoint_throwsException() {
        expectedException.expect(NullPointerException.class);

        NioHandler handler = makeNioHandler();
        ChannelHandlerContext context = mock(ChannelHandlerContext.class);
        HandshakeFutureListener listener = new HandshakeFutureListener(null, handler, context);
    }

    @Test
    public void construct_nullHandler_throwsException() {
        expectedException.expect(NullPointerException.class);

        EndPoint endPoint = new NioServer();
        ChannelHandlerContext context = mock(ChannelHandlerContext.class);
        HandshakeFutureListener listener = new HandshakeFutureListener(endPoint, null, context);
    }

    @Test
    public void construct_nullContext_throwsException() {
        expectedException.expect(NullPointerException.class);

        EndPoint endPoint = new NioServer();
        NioHandler handler = makeNioHandler(endPoint);
        HandshakeFutureListener listener = new HandshakeFutureListener(endPoint, handler, null);
    }

    /*@Test
    public void operationComplete_nonSuccesfulFuture_doesNothing() throws Exception {
        ConnectionListener mockListener = mock(ConnectionListener.class);
        Connection stubConnection = mock(Connection.class);

        Attribute<Connection> mockAttribute = mock(Attribute.class);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(mockAttribute);

        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        when(stubCtx.channel()).thenReturn(stubChannel);

        EndPoint endPoint = new NioServer();
        endPoint.addListener(mockListener);
        NioHandler handler = makeNioHandler(endPoint);

        Future<? super Channel> stubFuture = mock(Future.class);
        when(stubFuture.isSuccess()).thenReturn(true);

        HandshakeFutureListener listener = new HandshakeFutureListener(endPoint, handler, stubCtx);
        listener.operationComplete(stubFuture);

        verify(mockAttribute).set(ArgumentMatchers.any(NioConnection.class));
        verify(mockListener).connectionActive(stubConnection);
    }*/

    private NioHandler makeNioHandler(EndPoint endPoint) {
        return new NioHandler(endPoint);
    }

    private NioHandler makeNioHandler() {
        return makeNioHandler(new NioServer());
    }

}
