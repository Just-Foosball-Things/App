package nl.jft.network.nio;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.NotSslRecordException;
import io.netty.handler.ssl.SslHandler;
import nl.jft.network.Connection;
import nl.jft.network.EndPoint;
import nl.jft.network.listener.ConnectionListener;
import nl.jft.network.message.Message;

/**
 * A {@code NioHandler} takes care of all network related events occurred on {@link Channel}. It delegates the events
 * to {@link nl.jft.network.listener.ConnectionListener listeners} and {@link nl.jft.network.message.MessageHandler handlers}.
 *
 * @author Lesley
 */
final class NioHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(NioHandler.class.getSimpleName());

    private final EndPoint endPoint;

    /**
     * Initializes this {@code NioHandler} using the given {@link EndPoint}.
     *
     * @param endPoint The {@code EndPoint} that this {@code NioHandler} uses for certain meta-data properties, should not {@code null}.
     */
    NioHandler(EndPoint endPoint) {
        this.endPoint = Objects.requireNonNull(endPoint);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SslHandler handler = ctx.pipeline().get(SslHandler.class);
        handler.handshakeFuture().addListener(new HandshakeFutureListener(endPoint, this, ctx));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Connection connection = channel.attr(NioConstants.ATTRIBUTE_CONNECTION).getAndSet(null);

        if (connection != null) {
            connection.close();

            for (ConnectionListener listener : endPoint.getListeners()) {
                listener.connectionInactive(connection);
            }
        }

        logger.log(Level.INFO, String.format("[%s] Connection (%s) inactive.", getEndPointIdentifier(), channel));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
        if (!(object instanceof Message)) {
            throw new IllegalArgumentException(String.format("Received incorrect object (%s).", object));
        }

        Channel channel = ctx.channel();
        Connection connection = channel.attr(NioConstants.ATTRIBUTE_CONNECTION).get();

        Message message = (Message) object;
        endPoint.getMessageHandlers().notify(connection, message);

        logger.log(Level.INFO, String.format("[%s] Connection (%s) received message (%s).", getEndPointIdentifier(), channel, message));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();

        if (cause instanceof NotSslRecordException) {
            return; // Suppress this message to avoid spam (it's not important anyways, we deal with it accordingly).
        }

        logger.log(Level.INFO, String.format("[%s] An exception occurred on a connection (%s).", getEndPointIdentifier(), ctx.channel()), cause);
    }

    /**
     * Returns a display-friendly {@link String} containing the name of the {@link EndPoint} backed up by this {@code NioHandler}.
     *
     * @return A {@link String} containing the name of the {@code EndPoint} this {@code NioHandler} uses.
     */
    public String getEndPointIdentifier() {
        return endPoint.getClass().getSimpleName();
    }

}
