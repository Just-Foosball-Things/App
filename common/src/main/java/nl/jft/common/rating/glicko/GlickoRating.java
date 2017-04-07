package nl.jft.common.rating.glicko;

import nl.jft.common.rating.Rating;
import nl.jft.common.util.Arguments;
import nl.jft.common.util.Numbers;

/**
 * Holds a players Glicko-2 rating.
 * <p>
 * A Glicko-2 rating approaches the skill value of a player through numerical means.
 * This is done by using a standard deviation and volatility.
 * The standard deviation represents the certainty about a players rating and the volatility represents the consistency of a player.
 *
 * @author Oscar de Leeuw
 */
public class GlickoRating implements Rating {

    private final double rating;
    private final double deviation;
    private final double volatility;

    /**
     * Creates a new {@code GlickoRating}.
     * All values must be non-zero and not negative.
     *
     * @param rating     The rating of the player.
     * @param deviation  The rating deviation of the player.
     * @param volatility The volatility of the player.
     */
    public GlickoRating(double rating, double deviation, double volatility) {
        this.rating = Arguments.requirePositive(rating);
        this.deviation = Arguments.requirePositive(deviation);
        this.volatility = Arguments.requirePositive(volatility);
    }

    /**
     * Gets the rating of the player.
     *
     * @return A {@code double} that represents the rating of the player.
     */
    @Override
    public double getValue() {
        return rating;
    }

    /**
     * Gets the deviation of the player, represents the range of ratings the player is likely in.
     * For newer players this range is bigger than experienced players.
     *
     * @return A {@code double} that represents the deviation.
     */
    public double getDeviation() {
        return deviation;
    }

    /**
     * Gets the volatility of the player. The more consistent a player plays the lower the volatility.
     *
     * @return A {@code double} that represents the volatility of the player.
     */
    public double getVolatility() {
        return volatility;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.valueOf(rating).hashCode();
        result = 31 * result + Double.valueOf(deviation).hashCode();
        result = 31 * result + Double.valueOf(volatility).hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GlickoRating)) {
            return false;
        }

        GlickoRating other = (GlickoRating) obj;

        return Numbers.checkEqual(rating, other.rating) && Numbers.checkEqual(deviation, other.deviation) && Numbers.checkEqual(volatility, other.volatility);
    }
}
