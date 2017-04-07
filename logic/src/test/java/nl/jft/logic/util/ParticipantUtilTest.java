package nl.jft.logic.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nl.jft.logic.participant.Participant;
import nl.jft.logic.participant.ParticipantType;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class ParticipantUtilTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_whenCalled_throwsException() throws Throwable {
        expectedException.expect(UnsupportedOperationException.class);

        try {
            Constructor<ParticipantUtil> constructor = ParticipantUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Test
    public void getParticipantType_nullParticipant_throwsException() {
        expectedException.expect(NullPointerException.class);

        ParticipantUtil.getParticipantType(null);
    }

    @Test
    public void getParticipantType_userParticipant_returnsSolo() {
        Participant participant = ObjectBuilder.user().build();

        ParticipantType expected = ParticipantType.SOLO;
        ParticipantType actual = ParticipantUtil.getParticipantType(participant);

        assertEquals(expected, actual);
    }

    @Test
    public void getParticipantType_teamParticipant_returnsTeam() {
        Participant participant = ObjectBuilder.team().build();

        ParticipantType expected = ParticipantType.TEAM;
        ParticipantType actual = ParticipantUtil.getParticipantType(participant);

        assertEquals(expected, actual);
    }

}
