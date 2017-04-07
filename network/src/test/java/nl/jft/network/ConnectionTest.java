package nl.jft.network;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Lesley
 */
public abstract class ConnectionTest<C extends Connection> {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    public abstract C makeConnection();

    @Test
    public final void write_nullMessage_throwsException() {
        expectedException.expect(NullPointerException.class);

        C connection = makeConnection();
        connection.write(null);
    }


}