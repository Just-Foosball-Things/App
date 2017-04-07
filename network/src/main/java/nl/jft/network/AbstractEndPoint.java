package nl.jft.network;

import org.nustaq.serialization.FSTConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.jft.network.listener.ConnectionListener;
import nl.jft.network.message.MessageHandlerChainSet;

/**
 * An {@code AbstractEndPoint} is a default implementation of {@link EndPoint}. It contains default methods for
 * all non-network related code.
 *
 * @author Lesley
 */
public abstract class AbstractEndPoint implements EndPoint {

    private final List<ConnectionListener> listeners = new ArrayList<>();

    private final FSTConfiguration serializer;
    private final MessageHandlerChainSet chainSet;

    /**
     * Initializes this {@code AbstractEndPoint} with the default serializer ({@link FSTConfiguration#getDefaultConfiguration()}) and the default
     * {@link MessageHandlerChainSet}.
     */
    public AbstractEndPoint() {
        this(FSTConfiguration.getDefaultConfiguration(), new MessageHandlerChainSet());
    }

    /**
     * Initializes this {@code AbstractEndPoint} with a given serializer and the default {@link MessageHandlerChainSet}.
     *
     * @param serializer The {@link FSTConfiguration serializer} this {@code AbstractEndPoint} uses, should not be {@code null}.
     * @throws NullPointerException If the given {@code serializer} is {@code null}.
     */
    public AbstractEndPoint(FSTConfiguration serializer) {
        this(serializer, new MessageHandlerChainSet());
    }

    /**
     * Initializes this {@code AbstractEndPoint} with a given {@link MessageHandlerChainSet} and the
     * default serializer ({@link FSTConfiguration#getDefaultConfiguration()}).
     *
     * @param chainSet The {@code MessageHandlerChainSet} this {@code AbstractEndPoint} uses, should not be {@code null}.
     * @throws NullPointerException If the given {@code MessageHandlerChainSet} is {@code null}.
     */
    public AbstractEndPoint(MessageHandlerChainSet chainSet) {
        this(FSTConfiguration.getDefaultConfiguration(), chainSet);
    }

    /**
     * Initialzies this {@code AbstractEndPoint} with the given {@link MessageHandlerChainSet} and {@link FSTConfiguration serializer}.
     *
     * @param serializer The {@code serializer} this {@code AbstractEndPoint} uses, should not be {@code null}.
     * @param chainSet   The {@code MessageHandlerChainSet} this {@code AbstractEndPoint} uses, should not be {@code null}.
     * @throws NullPointerException If the given {@code serializer} is {@code null}.
     * @throws NullPointerException If the given {@code MessageHandlerChainSet} is {@code null}.
     */
    public AbstractEndPoint(FSTConfiguration serializer, MessageHandlerChainSet chainSet) {
        this.serializer = Objects.requireNonNull(serializer);
        this.chainSet = Objects.requireNonNull(chainSet);
    }

    @Override
    public final void addListener(ConnectionListener listener) {
        Objects.requireNonNull(listener);

        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    @Override
    public final void removeListener(ConnectionListener listener) {
        Objects.requireNonNull(listener);

        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    @Override
    public final List<ConnectionListener> getListeners() {
        List<ConnectionListener> list = new ArrayList<>();

        synchronized (listeners) {
            list.addAll(listeners);
        }

        return list;
    }

    @Override
    public final FSTConfiguration getFstConfiguration() {
        return serializer;
    }

    @Override
    public final MessageHandlerChainSet getMessageHandlers() {
        return chainSet;
    }
}
