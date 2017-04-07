package nl.jft.common.rating.glicko;

/**
 * The {@code ScoreCalculator} maps the outcome of a {@link GlickoResult} to a value between 0 and 1.
 *
 * @author Oscar de Leeuw
 */
@FunctionalInterface
public interface ScoreCalculator {
    /**
     * Calculates the actual score from a game.
     *
     * @param goalDifference The difference in goals between the winner and the loser.
     * @param maxGoals       The maximum amount of goals for that game.
     * @return a {@code double} that is the actual score for the winner of the game.
     */
    double getActualScore(int goalDifference, int maxGoals);
}
