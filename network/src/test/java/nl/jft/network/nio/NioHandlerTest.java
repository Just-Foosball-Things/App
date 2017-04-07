package nl.jft.network.nio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.NotSslRecordException;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.Attribute;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import nl.jft.network.Connection;
import nl.jft.network.EndPoint;
import nl.jft.network.listener.ConnectionListener;
import nl.jft.network.util.mocks.FakeMessage;
import nl.jft.network.util.mocks.FakeMessageHandler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Lesley
 */
public class NioHandlerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullEndPoint_throwsException() {
        expectedException.expect(NullPointerException.class);

        NioHandler handler = new NioHandler(null);
    }

    @Test
    public void channelActive_whenCalled_addListener() throws Exception {
        NioHandler handler = makeNioHandler();

        Future<Channel> mockFuture = mock(Future.class);

        SslHandler stubHandler = mock(SslHandler.class);
        when(stubHandler.handshakeFuture()).thenReturn(mockFuture);

        ChannelPipeline stubPipeline = mock(ChannelPipeline.class);
        when(stubPipeline.get(SslHandler.class)).thenReturn(stubHandler);

        ChannelHandlerContext fakeCtx = mock(ChannelHandlerContext.class);
        when(fakeCtx.pipeline()).thenReturn(stubPipeline);

        handler.channelActive(fakeCtx);

        verify(mockFuture).addListener(ArgumentMatchers.any(GenericFutureListener.class));
    }

    @Test
    public void channelInactive_whenCalledWithNullConnection_removesAttribute() throws Exception {
        NioHandler handler = makeNioHandler();

        Attribute<Connection> mockAttribute = mock(Attribute.class);
        when(mockAttribute.getAndSet(ArgumentMatchers.isNull())).thenReturn(null);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(mockAttribute);

        ChannelHandlerContext fakeCtx = mock(ChannelHandlerContext.class);
        when(fakeCtx.channel()).thenReturn(stubChannel);

        handler.channelInactive(fakeCtx);

        verify(mockAttribute).getAndSet(ArgumentMatchers.isNull());
    }

    @Test
    public void channelInactive_whenCalledWithConnection_removesAttribute() throws Exception {
        NioHandler handler = makeNioHandler();

        Connection stubConnection = mock(Connection.class);

        Attribute<Connection> mockAttribute = mock(Attribute.class);
        when(mockAttribute.getAndSet(ArgumentMatchers.isNull())).thenReturn(stubConnection);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(mockAttribute);

        ChannelHandlerContext fakeCtx = mock(ChannelHandlerContext.class);
        when(fakeCtx.channel()).thenReturn(stubChannel);

        handler.channelInactive(fakeCtx);

        verify(mockAttribute).getAndSet(ArgumentMatchers.isNull());
    }

    @Test
    public void channelInactive_whenCalledWithConnection_closesConnection() throws Exception {
        NioHandler handler = makeNioHandler();

        Connection mockConnection = mock(Connection.class);

        Attribute<Connection> stubAttribute = mock(Attribute.class);
        when(stubAttribute.getAndSet(ArgumentMatchers.isNull())).thenReturn(mockConnection);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(stubAttribute);

        ChannelHandlerContext fakeCtx = mock(ChannelHandlerContext.class);
        when(fakeCtx.channel()).thenReturn(stubChannel);

        handler.channelInactive(fakeCtx);

        verify(mockConnection).close();
    }

    @Test
    public void channelInactive_whenCalledWithConnection_invokesListeners() throws Exception {
        ConnectionListener mockListener = mock(ConnectionListener.class);

        EndPoint endPoint = new NioServer();
        endPoint.addListener(mockListener);
        NioHandler handler = makeNioHandler(endPoint);

        Connection stubConnection = mock(Connection.class);

        Attribute<Connection> mockAttribute = mock(Attribute.class);
        when(mockAttribute.getAndSet(ArgumentMatchers.isNull())).thenReturn(stubConnection);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(mockAttribute);

        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        when(stubCtx.channel()).thenReturn(stubChannel);

        handler.channelInactive(stubCtx);

        verify(mockListener).connectionInactive(stubConnection);
    }

    @Test
    public void channelRead_nonMessageObject_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        NioHandler handler = makeNioHandler();
        handler.channelRead(null, null);
    }

    @Test
    public void channelRead_whenCalled_invokesListeners() throws Exception {
        FakeMessageHandler mockHandler = new FakeMessageHandler();

        EndPoint endPoint = new NioServer();
        endPoint.getMessageHandlers().addHandler(mockHandler);
        NioHandler handler = makeNioHandler(endPoint);

        Connection stubConnection = mock(Connection.class);

        Attribute<Connection> mockAttribute = mock(Attribute.class);
        when(mockAttribute.get()).thenReturn(stubConnection);

        Channel stubChannel = mock(Channel.class);
        when(stubChannel.attr(NioConstants.ATTRIBUTE_CONNECTION)).thenReturn(mockAttribute);

        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        when(stubCtx.channel()).thenReturn(stubChannel);

        FakeMessage stubMessage = new FakeMessage();
        handler.channelRead(stubCtx, stubMessage);

        FakeMessage expected = stubMessage;
        FakeMessage actual = mockHandler.lastHandleFakeMessage;
        assertEquals(expected, actual);

        Connection expectedConnection = stubConnection;
        Connection actualConnection = mockHandler.lastHandleConnection;
        assertEquals(expectedConnection, actualConnection);
    }

    @Test
    public void exceptionCaught_whenCalled_closesContext() throws Exception {
        NioHandler handler = makeNioHandler();
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);

        handler.exceptionCaught(mockCtx, new Exception());

        verify(mockCtx).close();
    }

    @Test
    public void exceptionCaught_whenCalledWithNotSslRecordException_closesContext() throws Exception {
        NioHandler handler = makeNioHandler();
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);

        handler.exceptionCaught(mockCtx, new NotSslRecordException());

        verify(mockCtx).close();
    }

    @Test
    public void getEndPointIdentifier_whenCalled_returnsEndPointClassSimpleName() {
        EndPoint endPoint = new NioServer();
        NioHandler handler = makeNioHandler(endPoint);

        String expected = "NioServer";
        String actual = handler.getEndPointIdentifier();
        assertEquals(expected, actual);
    }

    private NioHandler makeNioHandler(EndPoint endPoint) {
        return new NioHandler(endPoint);
    }

    private NioHandler makeNioHandler() {
        return makeNioHandler(new NioServer());
    }

}
