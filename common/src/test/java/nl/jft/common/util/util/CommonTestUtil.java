package nl.jft.common.util.util;

import nl.jft.common.rating.glicko.GlickoCalculationResult;
import nl.jft.common.rating.glicko.GlickoRating;
import nl.jft.common.rating.glicko.GlickoResult;
import nl.jft.common.util.util.mocks.MockScoreCalculator;

/**
 * @author Oscar de Leeuw
 */
public class CommonTestUtil {

    /**
     * Creates a new {@code GlickoRating} with the following properties:
     * <li>
     * <ul>GlickoRating:      1500</ul>
     * <ul>Deviation:   350</ul>
     * <ul>Volatility:  0.06</ul>
     * </li>
     *
     * @return a default {@code GlickoRating}.
     */
    public static GlickoRating getDefaultRating() {
        return new GlickoRating(1500, 350, 0.06);
    }

    /**
     * Creates a new {@code GlickoResult} with the following properties:
     * <li>
     * <ul>Winner:          DefaultRating</ul>
     * <ul>Loser:           DefaultRating</ul>
     * <ul>GoalDifference:  5</ul>
     * <ul>MaxGoals:        10</ul>
     * </li>
     *
     * @return a default {@code GlickoResult}.
     */
    public static GlickoResult getDefaultResult() {
        return new GlickoResult(getDefaultRating(), getDefaultRating(), 5, 10, new MockScoreCalculator());
    }

    /**
     * Creates a new {@code GlickoCalculationResult} with the following properties:
     * <li>
     * <ul>Winner:      DefaultRating</ul>
     * <ul>Loser:       DefaultRating</ul>
     * </li>
     *
     * @return a default {@code GlickoCalculationResult}.
     */
    public static GlickoCalculationResult getDefaultCalculationResult() {
        return new GlickoCalculationResult(getDefaultRating(), getDefaultRating());
    }

}
