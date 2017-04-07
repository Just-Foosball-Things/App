package nl.jft.network;

import org.junit.Test;

/**
 * @author Lesley
 */
public abstract class ServerTest<E extends Server> extends EndPointTest<E> {

    @Test
    public final void bind_nullAddress_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        E server = makeEndPoint();
        server.bind(null);
    }

}