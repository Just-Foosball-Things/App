package nl.jft.logic.statistic.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.jft.logic.match.MatchResult;
import nl.jft.logic.statistic.Statistic;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class MatchStatisticTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getType_whenCalled_returnsSameClass() {
        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();

        Class<? extends Statistic> expected = MatchStatistic.class;
        Class<? extends Statistic> actual = statistic.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void addMatchResult_nullMatchResult_throwsException() {
        expectedException.expect(NullPointerException.class);

        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();
        statistic.addMatchResult(null);
    }

    @Test
    public void addMatchResult_whenCalled_addsMatchResult() {
        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();
        MatchResult result = ObjectBuilder.matchResult().build();

        statistic.addMatchResult(result);

        List<MatchResult> expected = Arrays.asList(ObjectBuilder.matchResult().build());
        List<MatchResult> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

    @Test
    public void removeMatchResult_nullMatchResult_throwsException() {
        expectedException.expect(NullPointerException.class);

        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();
        statistic.removeMatchResult(null);
    }

    @Test
    public void removeMatchResult_whenCalled_removesMatchResult() {
        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();
        MatchResult result = ObjectBuilder.matchResult().build();

        statistic.addMatchResult(result);
        statistic.removeMatchResult(result);

        List<MatchResult> expected = new ArrayList<>();
        List<MatchResult> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

    @Test
    public void getValues_byDefault_returnsEmptyList() {
        MatchStatistic statistic = ObjectBuilder.Statistic.match().build();

        List<MatchResult> expected = new ArrayList<>();
        List<MatchResult> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

}
