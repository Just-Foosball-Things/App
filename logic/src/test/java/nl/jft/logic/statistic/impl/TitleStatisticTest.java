package nl.jft.logic.statistic.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.jft.logic.participant.Title;
import nl.jft.logic.statistic.Statistic;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class TitleStatisticTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getType_whenCalled_returnsSameClass() {
        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();

        Class<? extends Statistic> expected = TitleStatistic.class;
        Class<? extends Statistic> actual = statistic.getType();

        assertEquals(expected, actual);
    }

    @Test
    public void addTitle_nullTitle_throwsException() {
        expectedException.expect(NullPointerException.class);

        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();
        statistic.addTitle(null);
    }

    @Test
    public void addTitle_whenCalled_addsTitle() {
        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();
        Title title = ObjectBuilder.title().build();

        statistic.addTitle(title);

        List<Title> expected = Arrays.asList(title);
        List<Title> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

    @Test
    public void removeTitle_nullTitle_throwsException() {
        expectedException.expect(NullPointerException.class);

        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();
        statistic.removeTitle(null);
    }

    @Test
    public void removeTitle_whenCalled_removesTitle() {
        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();
        Title title = ObjectBuilder.title().build();

        statistic.addTitle(title);
        statistic.removeTitle(title);

        List<Title> expected = new ArrayList<>();
        List<Title> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

    @Test
    public void getValues_byDefault_returnsEmptyList() {
        TitleStatistic statistic = ObjectBuilder.Statistic.title().build();

        List<Title> expected = new ArrayList<>();
        List<Title> actual = statistic.getValues();

        assertEquals(expected, actual);
    }

}
