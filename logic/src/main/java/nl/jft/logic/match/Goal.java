package nl.jft.logic.match;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import nl.jft.common.Identifiable;
import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Participant;

/**
 * A {@code Goal} represents a {@code Goal} made by a {@link nl.jft.logic.participant.Participant} in a {@link Match}.
 * It contains metadata about the {@code Goal} such as who scored it, and when it was scored.
 *
 * @author Lesley
 */
public class Goal implements Identifiable, Serializable {

    private final int id;
    private final Participant participant;
    private final Date time;

    /**
     * Initializes a new {@code Goal} by invoking an other constructor ({@link #Goal(int, Participant, Date)}.
     *
     * @param participant The {@code participant} who scored this {@code Goal}. Can be {@code null} to indicate the scorer is unknown.
     * @param time        The {@code time} at which this {@code Goal} was scored, should not be null.
     * @throws NullPointerException If {@code time} is {@code null}.
     */
    public Goal(Participant participant, Date time) {
        this(LogicConstants.INTERNAL_ID, participant, time);
    }

    /**
     * Initializes a new Goal using the given id, participant and time.
     *
     * @param id          The {@code id} (dependant on an external system such as a database) of this {@code Goal}.
     * @param participant The {@code participant} who scored this {@code Goal}. Can be {@code null} to indicate the scorer is unknown.
     * @param time        The {@code time} at which this {@code Goal} was scored, should not be null.
     * @throws NullPointerException If {@code time} is {@code null}.
     */
    public Goal(int id, Participant participant, Date time) {
        this.id = id;
        this.participant = participant;
        this.time = Objects.requireNonNull(time);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Goal)) {
            return false;
        }

        Goal o = (Goal) other;
        if ((o.participant == null && participant != null) || (o.participant != null && participant == null)) {
            return false;
        }

        boolean sameParticipants = o.participant == null && participant == null || o.participant.equals(participant);
        return o.id == id && sameParticipants && o.time.equals(time);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;

        if (participant != null) {
            result = 31 * result + participant.hashCode();
        }

        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets the {@link Participant} who scored this {@code Goal}. May be {@code null} if it is unknown who scored this {@code Goal}.
     *
     * @return The {@code Participant} who scored this {@code Goal}.
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * Gets the {@code time} at which this {@code Goal} was scored.
     *
     * @return The {@code time} at which this {@code Goal} was scored.
     */
    public Date getTime() {
        return time;
    }
}
