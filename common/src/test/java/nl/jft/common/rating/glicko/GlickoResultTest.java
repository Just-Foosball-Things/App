package nl.jft.common.rating.glicko;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.util.util.CommonTestUtil;
import nl.jft.common.util.util.mocks.MockScoreCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Oscar de Leeuw
 */
public class GlickoResultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructor_withNullFirstArgument_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        makeResult(null, CommonTestUtil.getDefaultRating(), 5, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withNullSecondArgument_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        makeResult(CommonTestUtil.getDefaultRating(), null, 5, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withSameArgument_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = winner;

        makeResult(winner, loser, 5, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withDifferentArgument_isNotNull() throws Exception {
        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, 10, new MockScoreCalculator());

        assertNotNull(glickoResult);
    }

    @Test
    public void constructor_withTooLargeGoalDifference_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 11, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withNegativeGoalDifference_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, -11, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withZeroGoalDifference_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 0, 10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withNegativeMaxGoal_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, -10, new MockScoreCalculator());
    }

    @Test
    public void constructor_withZeroMaxGoal_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, 0, new MockScoreCalculator());
    }

    @Test
    public void constuctor_withNullCalculator_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, 10, null);
    }

    @Test
    public void getWinner_withDefault_returnsRating() throws Exception {
        GlickoResult glickoResult = CommonTestUtil.getDefaultResult();

        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = glickoResult.getWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void getLoser_withDefault_returnsRating() throws Exception {
        GlickoResult glickoResult = CommonTestUtil.getDefaultResult();

        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = glickoResult.getLoser();

        assertEquals(expected, actual);
    }

    @Test
    public void getGoalDifference_withDefault_returnsInt() throws Exception {
        GlickoResult glickoResult = CommonTestUtil.getDefaultResult(); //GoalDifference = 5

        int expected = 5;
        int actual = glickoResult.getGoalDifference();

        assertEquals(expected, actual);
    }

    @Test
    public void getMaximumGoal_withDefault_returnsInt() throws Exception {
        GlickoResult glickoResult = CommonTestUtil.getDefaultResult(); //MaxGoals = 10

        int expected = 10;
        int actual = glickoResult.getMaxGoals();

        assertEquals(expected, actual);
    }

    @Test
    public void getWinnerScore_withDefault_callsInjection() throws Exception {
        MockScoreCalculator calculator = new MockScoreCalculator();
        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, 10, calculator);
        glickoResult.getWinnerScore();

        assertTrue(calculator.hasBeenCalled);
    }

    @Test
    public void getLoserScore_withDefault_callsInjection() throws Exception {
        MockScoreCalculator calculator = new MockScoreCalculator();
        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoResult glickoResult = makeResult(winner, loser, 5, 10, calculator);
        glickoResult.getLoserScore();

        assertTrue(calculator.hasBeenCalled);
    }

    @Test
    public void getLoserScore_withDefault_returnsDouble() throws Exception {
        GlickoResult result = CommonTestUtil.getDefaultResult();

        double winnerScore = result.getWinnerScore(); //MockScoreCalculator will return 0.
        double expected = 1;
        double actual = result.getLoserScore();

        assertEquals(expected, actual, 0.001d);
    }

    private GlickoResult makeResult(GlickoRating winner, GlickoRating loser, int goalDif, int maxGoal, ScoreCalculator calculator) {
        return new GlickoResult(winner, loser, goalDif, maxGoal, calculator);
    }
}
