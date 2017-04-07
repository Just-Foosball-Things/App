package nl.jft.common.rating.glicko;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.common.util.util.CommonTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Oscar de Leeuw
 */
public class StandardGlickoCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private StandardGlickoCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StandardGlickoCalculator();
    }

    @Test
    public void mu_with1500Argument_returnsDouble() throws Exception {
        double expected = 0;
        double actual = calculator.mu(1500);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void mu_with2000Argument_returnsDouble() throws Exception {
        double expected = 2.8782312;
        double actual = calculator.mu(2000);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void mu_with1000Argument_returnsDouble() throws Exception {
        double expected = -2.87823124631;
        double actual = calculator.mu(1000);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void mu_with1400Argument_returnsDouble() throws Exception {
        double expected = -0.5756;
        double actual = calculator.mu(1400);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void mu_with1550Argument_returnsDouble() throws Exception {
        double expected = 0.2878;
        double actual = calculator.mu(1550);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void mu_with1700Argument_returnsDouble() throws Exception {
        double expected = 1.1513;
        double actual = calculator.mu(1700);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phi_with350Argument_returnsDouble() throws Exception {
        double expected = 2.01476187242;
        double actual = calculator.phi(350);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phi_with250Argument_returnsDouble() throws Exception {
        double expected = 1.43911562315;
        double actual = calculator.phi(250);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phi_with500Argument_returnsDouble() throws Exception {
        double expected = 2.87823124631;
        double actual = calculator.phi(500);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phi_with30Argument_returnsDouble() throws Exception {
        double expected = 0.1727;
        double actual = calculator.phi(30);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phi_with100Argument_returnsDouble() throws Exception {
        double expected = 0.5756;
        double actual = calculator.phi(100);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phi_with300Argument_returnsDouble() throws Exception {
        double expected = 1.7269;
        double actual = calculator.phi(300);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phi_with200Argument_returnsDouble() throws Exception {
        double expected = 1.1513;
        double actual = calculator.phi(200);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void g_with500Argument_returnsDouble() throws Exception {
        double expected = 0.53314574144;
        double actual = calculator.g(500);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void g_with350Argument_returnsDouble() throws Exception {
        double expected = 0.6690694125804828;
        double actual = calculator.g(350);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void g_with300Argument_returnsDouble() throws Exception {
        double expected = 0.724235478087;
        double actual = calculator.g(300);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void g_with200Argument_returnsDouble() throws Exception {
        double expected = 0.8442815045061;
        double actual = calculator.g(200);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void g_with100Argument_returnsDouble() throws Exception {
        double expected = 0.9531;
        double actual = calculator.g(100);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void g_with30Argument_returnsDouble() throws Exception {
        double expected = 0.9955;
        double actual = calculator.g(30);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void e_with1500Rating2000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 0.1272232377;
        double actual = calculator.e(1500, 2000, 350);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void e_with1000Rating3000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 0.00045129138;
        double actual = calculator.e(1000, 3000, 350);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void e_with1500Rating1500Rating200Deviation_returnsDouble() throws Exception {
        double expected = 0.5;
        double actual = calculator.e(1500, 1500, 200);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void e_with1500Rating1500Rating250Deviation_returnsDouble() throws Exception {
        double expected = 0.5;
        double actual = calculator.e(1500, 1500, 250);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void e_with1500Rating1400Rating30Deviation_returnsDouble() throws Exception {
        double expected = 0.639;
        double actual = calculator.e(1500, 1400, 30);

        assertEquals(expected, actual, 0.001d);
    }

    @Test
    public void e_with1500Rating1550Rating100Deviation_returnsDouble() throws Exception {
        double expected = 0.432;
        double actual = calculator.e(1500, 1550, 100);

        assertEquals(expected, actual, 0.001d);
    }

    @Test
    public void e_with1500Rating1700Rating300Deviation_returnsDouble() throws Exception {
        double expected = 0.303;
        double actual = calculator.e(1500, 1700, 300);

        assertEquals(expected, actual, 0.001d);
    }

    @Test
    public void v_with1500Rating2000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 20.1181494346357595;
        double actual = calculator.v(1500, 2000, 350);

        //e = 0.1272232377
        //g = 0.6690694125804828

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void v_with1000Rating3000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 4952.18257166890839;
        double actual = calculator.v(1000, 3000, 350);

        //e = 0.00045129138
        //g = 0.6690694125804828

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void v_with1500Rating1500Rating200Deviation_returnsDouble() throws Exception {
        double expected = 5.61158364199695;
        double actual = calculator.v(1500, 1500, 200);

        //e = 0.5
        //g = 0.8442815045061

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void delta_with1500Rating2000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 11.747957867219615;
        double actual = calculator.delta(1500, 2000, 350, 1);

        //v = 20.1181494346357595
        //e = 0.1272232377
        //g = 0.6690694125804828

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void delta_with1000Rating3000Rating350Deviation_returnsDouble() throws Exception {
        double expected = 1655.18165406207357949;
        double actual = calculator.delta(1000, 3000, 350, 0.5);

        //v = 4952.18257166890839;
        //e = 0.00045129138
        //g = 0.6690694125804828

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void delta_with1500Rating1500Rating200Deviation_returnsDouble() throws Exception {
        double expected = -2.3688781399635;
        double actual = calculator.delta(1500, 1500, 200, 0);

        //v = 5.61158364199695
        //e = 0.5
        //g = 0.8442815045061

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void a_with006Volatility_returnsDouble() throws Exception {
        double expected = -5.626821433520073;
        double actual = calculator.a(0.06);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void a_with007Volatility_returnsDouble() throws Exception {
        double expected = -5.318520073865556;
        double actual = calculator.a(0.07);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void a_with005Volatility_returnsDouble() throws Exception {
        double expected = -5.991464547107982;
        double actual = calculator.a(0.05);

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void f_testOne_returnsDouble() throws Exception {
        double expected = -19.7201890814611;
        double actual = calculator.f(1, 11.747957867219615, 1.43911562315, 20.1181494346357595, -10.231991619, 0.75);

        //delta = 11.747957867219615
        //v = 20.1181494346357595
        //phi = 1.43911562315
        //a = -10.231991619
        //tau = 0.75

        assertEquals(expected, actual, 0.0000001d);
    }

    @Test
    public void f_testTwo_returnsDouble() throws Exception {
        double expected = -19.26875089204392;
        double actual = calculator.f(1, 1655.18165406207357949, 2.01476187242, 4952.18257166890839, -9.92369025985364, 0.75);

        //delta = 1655.18165406207357949
        //v = 4952.18257166890839
        //phi = 2.01476187242
        //a = -9.92369025985364
        //tau = 0.75

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void f_testThree_returnsDouble() throws Exception {
        double expected = -19.0668560505;
        double actual = calculator.f(0.1, -2.368878, 2.87823, 5.6115836, -10.5966347, 0.75);

        //delta = -2.3688781399635
        //v = 5.61158364199695
        //phi = 2.87823124631
        //a = -10.59663473309607
        //tau = 0.75

        assertEquals(expected, actual, 0.1d);
    }

    @Test
    public void newSigma_testOne_returnsDouble() throws Exception {
        double expected = 0.06;
        double actual = calculator.newSigma(-2.3688781399635, 2.87823124631, 5.61158364199695, -5.62682, 0.75, 0.000001);

        //delta = -2.3688781399635
        //v = 5.61158364199695
        //phi = 2.87823124631
        //a = -10.231991619
        //tau = 0.75
        //convergence = 0.000001

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newSigma_testTwo_returnsDouble() throws Exception {
        double expected = 0.06;
        double actual = calculator.newSigma(1655.18165406207357949, 2.01476187242, 4952.18257166890839, -5.62682, 0.75, 0.000001);

        //delta = 1655.18165406207357949
        //v = 4952.18257166890839
        //phi = 2.01476187242
        //a = -10.231991619
        //tau = 0.75
        //convergence = 0.000001

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newSigma_testThree_returnsDouble() throws Exception {
        double expected = 0.05999;
        double actual = calculator.newSigma(-0.4834, 1.1513, 1.7785, -5.62682, 0.5, 0.000001);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phiStar_testOne_returnsDouble() throws Exception {
        double expected = 2.01565505049;
        double actual = calculator.phiStar(2.01476187242, 0.05999);

        //phi = 2.01476187242 (350 deviation)
        //sigma' = 0.05999

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phiStar_testTwo_returnsDouble() throws Exception {
        double expected = 2.87885656246;
        double actual = calculator.phiStar(2.87823124631, 0.06);

        //phi =  2.87823124631 (500 deviation)
        //sigma' = 0.06

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void phiStar_testThree_returnsDouble() throws Exception {
        double expected = 0.18634722965;
        double actual = calculator.phiStar(0.1727, 0.07);

        //phi =  0.1727 (30 deviation)
        //sigma' = 0.07

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newPhi_testOne_returnsDouble() throws Exception {
        double expected = 1.83853950147;
        double actual = calculator.newPhi(2.01565505049, 20.1181494346357595);

        //phiStar = 2.01565505049 (350 deviation, 0.05999 vol)
        //v = 20.1181494346357595 (1500 rating, 2000 opRating, 350 opDeviation)

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newPhi_testTwo_returnsDouble() throws Exception {
        double expected = 1.82921494119;
        double actual = calculator.newPhi(2.87885656246, 5.611583641996955);

        //phiStar = 2.87885656246 (500 deviation, 0.06 vol)
        //v = 5.61158364199695 (1500 rating, 1500 opRating, 200 opDeviation)

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newPhi_testThree_returnsDouble() throws Exception {
        double expected = 0.1863465763;
        double actual = calculator.newPhi(0.18634722965, 4952.18257166890839);

        //phiStar = 0.18634722965 (30 deviation, 0.07 vol)
        //v = 4952.18257166890839 (1000 rating, 3000 opRating, 350 opDeviation)

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newMu_testOne_returnsDouble() throws Exception {
        //1500/500 ratingA vs 1500/200 ratingB, A lost
        //0.06 vol

        double mu = 0;
        double newPhi = 1.82921494119;
        double g = 0.8442815045061;
        double s = 0d;
        double e = 0.5;

        double expected = -1.41249448193;
        double actual = calculator.newMu(mu, newPhi, g, s, e);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newMu_testTwo_returnsDouble() throws Exception {
        //1000/30 ratingA vs 3000/350 ratingB, Draw
        //0.07 vol

        double mu = -2.87823124631;
        double newPhi = 0.1863465763;
        double g = 0.6690694125804828;
        double s = 0.5;
        double e = 0.00045129138;

        double expected = -2.86662499814;
        double actual = calculator.newMu(mu, newPhi, g, s, e);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void newMu_testThree_returnsDouble() throws Exception {
        //1500/350 ratingA vs 2000/350 ratingB, A won
        //0.06 vol

        double mu = 0d;
        double newPhi = 1.83853950147;
        double g = 0.6690694125804828;
        double s = 1;
        double e = 0.1272232377;

        double expected = 1.97387788388;
        double actual = calculator.newMu(mu, newPhi, g, s, e);

        assertEquals(expected, actual, 0.0001d);
    }

    @Test
    public void muToRating_testOne_returnsDouble() throws Exception {
        double expected = 1500;
        double actual = calculator.muToRating(0);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void muToRating_testTwo_returnsDouble() throws Exception {
        double expected = 2000;
        double actual = calculator.muToRating(2.8782312);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void muToRating_testThree_returnsDouble() throws Exception {
        double expected = 1000;
        double actual = calculator.muToRating(-2.87823124631);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phiToRating_testOne_returnsDouble() throws Exception {
        double expected = 200;
        double actual = calculator.phiToRating(1.1513);

        assertEquals(expected, actual, 0.1d);
    }

    @Test
    public void phiToRating_testTwo_returnsDouble() throws Exception {
        double expected = 350;
        double actual = calculator.phiToRating(2.01476187242);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void phiToRating_testThree_returnsDouble() throws Exception {
        double expected = 500;
        double actual = calculator.phiToRating(2.87823124631);

        assertEquals(expected, actual, 0.00001d);
    }

    @Test
    public void getNewRating_testOne_returnsGlickoRating() throws Exception {
        GlickoRating winner = new GlickoRating(1400, 30, 0.06);
        GlickoRating loser = new GlickoRating(1550, 100, 0.06);

        GlickoRating rating = calculator.getNewRating(winner, loser, 1);

        assertEquals(1403.8210, rating.getValue(), 0.001d);
        assertEquals(31.657, rating.getDeviation(), 0.001d);
        assertEquals(0.060007343, rating.getVolatility(), 0.00000001d);
    }

    @Test
    public void getNewRating_testTwo_returnsGlickoRating() throws Exception {
        GlickoRating winner = new GlickoRating(1400, 30, 0.06);
        GlickoRating loser = new GlickoRating(1550, 100, 0.06);

        GlickoRating rating = calculator.getNewRating(loser, winner, 0);

        assertEquals(1511.9412, rating.getValue(), 0.001d);
        assertEquals(97.2262, rating.getDeviation(), 0.001d);
        assertEquals(0.06000711, rating.getVolatility(), 0.00000001d);
    }

    @Test
    public void getNewRating_testThree_returnsGlickoRating() throws Exception {
        GlickoRating winner = new GlickoRating(1545, 300, 0.06);
        GlickoRating loser = new GlickoRating(1743, 200, 0.06);

        GlickoRating rating = calculator.getNewRating(winner, loser, 0.6);

        assertEquals(1644.3946, rating.getValue(), 0.001d);
        assertEquals(251.4019, rating.getDeviation(), 0.001d);
        assertEquals(0.059998, rating.getVolatility(), 0.000001d);
    }

    @Test
    public void getNewRating_testFour_returnsGlickoRating() throws Exception {
        GlickoRating winner = new GlickoRating(1545, 300, 0.06);
        GlickoRating loser = new GlickoRating(1743, 200, 0.06);

        GlickoRating rating = calculator.getNewRating(loser, winner, 0.4);

        assertEquals(1699.9609, rating.getValue(), 0.001d);
        assertEquals(186.9439, rating.getDeviation(), 0.001d);
        assertEquals(0.059998, rating.getVolatility(), 0.000001d);
    }

    @Test
    public void calculateNewRating_withDefault_returnsGlickoCalculationResult() throws Exception {
        GlickoResult glickoResult = CommonTestUtil.getDefaultResult();

        GlickoCalculationResult result = calculator.calculateNewRating(glickoResult);

        assertNotNull(result.getWinner());
        assertNotNull(result.getLoser());
    }
}
