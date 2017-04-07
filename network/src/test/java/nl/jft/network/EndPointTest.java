package nl.jft.network;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.nustaq.serialization.FSTConfiguration;

import nl.jft.network.message.MessageHandlerChainSet;

/**
 * @author Lesley
 */
public abstract class EndPointTest<E extends EndPoint> {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    public abstract E makeEndPoint();

    public abstract E makeEndPoint(FSTConfiguration serializer, MessageHandlerChainSet chainSet);

    @Test
    public final void construct_nullSerializer_throwsException() {
        expectedException.expect(NullPointerException.class);

        E endPoint = makeEndPoint(null, new MessageHandlerChainSet());
    }

    @Test
    public final void construct_nullChainSet_throwsException() {
        expectedException.expect(NullPointerException.class);

        E endPoint = makeEndPoint(FSTConfiguration.getDefaultConfiguration(), null);
    }

    @Test
    public final void addListener_nullListener_throwsException() {
        expectedException.expect(NullPointerException.class);

        E endPoint = makeEndPoint();
        endPoint.addListener(null);
    }

    @Test
    public final void removeListener_nullListener_throwsException() {
        expectedException.expect(NullPointerException.class);

        E endPoint = makeEndPoint();
        endPoint.removeListener(null);
    }

}
