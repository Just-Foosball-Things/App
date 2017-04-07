package nl.jft.common.rating.glicko;

/**
 * The {@code GlickoCalculator} calculates new {@link GlickoRating GlickoRatings} based on a {@link GlickoResult}.
 * This interface describes the basic methods that such a calculator requires, but the implementation of these is trivial.
 * <p>
 * For more information about the Glicko-2 rating system please consult www.glicko.net/glicko/glicko2.pdf.
 * The Glicko-2 rating system is public domain and was invented by Mark Glickman.
 *
 * @author Oscar de Leeuw
 */
@FunctionalInterface
public interface GlickoCalculator {

    /**
     * Calculates the new {@link GlickoRating GlickoRatings} for the two players from a {@code GlickoResult}.
     *
     * @param result The new {@code GlickoRatings} of the two players.
     * @return A {@code GlickoCalculationResult}.
     */
    GlickoCalculationResult calculateNewRating(GlickoResult result);
}
