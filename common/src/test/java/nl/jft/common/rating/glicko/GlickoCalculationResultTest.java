package nl.jft.common.rating.glicko;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.util.util.CommonTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Oscar de Leeuw
 */
public class GlickoCalculationResultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructor_withNullFirstArgument_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        makeResult(null, CommonTestUtil.getDefaultRating());
    }

    @Test
    public void constructor_withNullSecondArgument_throwsException() throws Exception {
        expectedException.expect(NullPointerException.class);

        makeResult(CommonTestUtil.getDefaultRating(), null);
    }

    @Test
    public void constructor_withSameArgument_throwsException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);

        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = winner;

        makeResult(winner, loser);
    }

    @Test
    public void constructor_withDifferentArgument_isNotNull() throws Exception {
        GlickoRating winner = CommonTestUtil.getDefaultRating();
        GlickoRating loser = CommonTestUtil.getDefaultRating();

        GlickoCalculationResult result = makeResult(winner, loser);

        assertNotNull(result);
    }

    @Test
    public void getWinner_withDefault_returnsRating() throws Exception {
        GlickoCalculationResult result = CommonTestUtil.getDefaultCalculationResult();

        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = result.getWinner();

        assertEquals(expected, actual);
    }

    @Test
    public void getLoser_withDefault_returnsRating() throws Exception {
        GlickoCalculationResult result = CommonTestUtil.getDefaultCalculationResult();

        GlickoRating expected = CommonTestUtil.getDefaultRating();
        GlickoRating actual = result.getLoser();

        assertEquals(expected, actual);
    }

    private GlickoCalculationResult makeResult(GlickoRating winner, GlickoRating loser) {
        return new GlickoCalculationResult(winner, loser);
    }
}
