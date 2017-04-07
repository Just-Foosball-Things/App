package nl.jft.common.rating.glicko;

import nl.jft.common.util.Arguments;

/**
 * The {@code GlickoCalculationResult} stores the results of a {@link GlickoCalculator} calculation.
 * It stores the new {@link GlickoRating} for the winner and the loser of a {@link GlickoResult}.
 * {@code GlickoCalculationResult} assumes that the new rating for the winner and loser are correctly passed through the constructor.
 * As there is no additional check to verify that the new rating, for the winner or loser in this {@code GlickoCalculationResult},
 * belong to the corresponding winner or loser in the {@code GlickoResult}.
 *
 * @author Oscar de Leeuw
 */
public class GlickoCalculationResult {

    private final GlickoRating winner;
    private final GlickoRating loser;

    /**
     * Creates a new {@code GlickoCalculationResult}.
     *
     * @param winner The new {@code GlickoRating} of the winner.
     * @param loser  The new {@code GlickoRating} of the loser.
     */
    public GlickoCalculationResult(GlickoRating winner, GlickoRating loser) {
        this.winner = Arguments.requireNotSame(winner, loser);
        this.loser = Arguments.requireNotSame(loser, winner);
    }

    /**
     * Gets the new {@code GlickoRating} of the winner.
     *
     * @return A {@code GlickoRating}.
     */
    public GlickoRating getWinner() {
        return winner;
    }

    /**
     * Gets the new {@code GlickoRating} of the loser.
     *
     * @return A {@code GlickoRating}.
     */
    public GlickoRating getLoser() {
        return loser;
    }
}
