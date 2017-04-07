package nl.jft.network;

import org.junit.Test;

/**
 * @author Lesley
 */
public abstract class ClientTest<E extends Client> extends EndPointTest<E> {

    @Test
    public final void bind_nullAddress_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        E client = makeEndPoint();
        client.connect(null);
    }


}