package nl.jft.logic.util.builder;

import nl.jft.common.rating.glicko.GlickoRating;

/**
 * @author Lesley
 */
public final class GlickoRatingBuilder {

    private double rating;
    private double deviation;
    private double volatility;

    public GlickoRatingBuilder() {
        rating = 1500;
        deviation = 350;
        volatility = 0.06;
    }

    public GlickoRatingBuilder withRating(double rating) {
        this.rating = rating;
        return this;
    }

    public GlickoRatingBuilder withDeviation(double deviation) {
        this.deviation = deviation;
        return this;
    }

    public GlickoRatingBuilder withVolatility(double volatility) {
        this.volatility = volatility;
        return this;
    }

    public GlickoRating build() {
        return new GlickoRating(rating, deviation, volatility);
    }

}
