package nl.jft.logic.participant;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

import nl.jft.common.Identifiable;
import nl.jft.common.rating.Rating;
import nl.jft.logic.participant.impl.User;

/**
 * The {@code Participant} interface defines a participant that can partake in {@link nl.jft.logic.match.Match Matches} and {@link nl.jft.logic.tournament.Tournament Tournamements}.
 * A {@code Participant} is stored in the database and thus should be {@code Identifiable}.
 *
 * @author Lesley
 * @author Oscar de Leeuw
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = User.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class, name = "participant")})
public interface Participant extends Identifiable, Serializable {

    /**
     * Gets the name of the {@code Participant}.
     *
     * @return A {@code String} that represents the name of the {@code Participant}.
     */
    String getName();

    /**
     * Gets the {@link Rating} of the {@code Participant}.
     *
     * @return A {@code Rating} containing the rating of the {@code Participant}.
     */
    Rating getRating();

}
