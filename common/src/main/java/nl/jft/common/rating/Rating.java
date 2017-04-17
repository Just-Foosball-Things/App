package nl.jft.common.rating;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

import nl.jft.common.rating.glicko.GlickoRating;

/**
 * @author Lesley
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = GlickoRating.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GlickoRating.class, name = "rating")})
public interface Rating extends Serializable {

    double getValue();

}
