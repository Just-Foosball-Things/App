package nl.jft.network.nio;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import nl.jft.network.Connection;
import nl.jft.network.EndPoint;
import nl.jft.network.listener.ConnectionListener;

/**
 * @author Lesley
 */
public final class HandshakeFutureListener implements GenericFutureListener<Future<? super Channel>> {

    private static final Logger logger = Logger.getLogger(HandshakeFutureListener.class.getSimpleName());

    private final EndPoint endPoint;
    private final NioHandler handler;
    private final ChannelHandlerContext ctx;

    public HandshakeFutureListener(EndPoint endPoint, NioHandler handler, ChannelHandlerContext ctx) {
        this.endPoint = Objects.requireNonNull(endPoint);
        this.handler = Objects.requireNonNull(handler);
        this.ctx = Objects.requireNonNull(ctx);
    }

    @Override
    public void operationComplete(Future<? super Channel> future) throws Exception {
        if (!future.isSuccess()) {
            return;
        }

        Channel channel = ctx.channel();
        Connection connection = new NioConnection(channel);

        channel.attr(NioConstants.ATTRIBUTE_CONNECTION).set(connection);
        for (ConnectionListener listener : endPoint.getListeners()) {
            listener.connectionActive(connection);
        }

        SslHandler sslHandler = ctx.pipeline().get(SslHandler.class);
        String suite = sslHandler.engine().getSession().getCipherSuite();
        logger.log(Level.INFO, String.format("[%s] Connection (%s) active (Protected by %s).", handler.getEndPointIdentifier(), channel, suite));

    }

}
