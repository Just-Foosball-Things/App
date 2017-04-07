package nl.jft.common.util.util.mocks;

import nl.jft.common.rating.glicko.ScoreCalculator;

/**
 * @author Oscar de Leeuw
 */
public class MockScoreCalculator implements ScoreCalculator {

    public boolean hasBeenCalled = false;

    @Override
    public double getActualScore(int goalDifference, int maxGoals) {
        hasBeenCalled = true;
        return 0;
    }
}
