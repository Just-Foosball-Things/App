package nl.jft.logic.statistic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nl.jft.logic.statistic.impl.RatingStatistic;
import nl.jft.logic.util.builder.ObjectBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Lesley
 */
public class StatisticCollectionTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addStatistic_nullStatistic_throwsException() {
        expectedException.expect(NullPointerException.class);

        StatisticCollection collection = ObjectBuilder.Statistic.collection().build();
        collection.addStatistic(null);
    }

    @Test
    public void addStatistic_whenCalled_addsStatistic() {
        StatisticCollection collection = ObjectBuilder.Statistic.collection().build();
        RatingStatistic statistic = ObjectBuilder.Statistic.rating().build();

        collection.addStatistic(statistic);

        RatingStatistic expected = statistic;
        RatingStatistic actual = collection.getStatistic(RatingStatistic.class);

        assertEquals(expected, actual);
    }

    @Test
    public void addStatistic_duplicateStatistic_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        StatisticCollection collection = ObjectBuilder.Statistic.collection().build();

        collection.addStatistic(ObjectBuilder.Statistic.match().build());
        collection.addStatistic(ObjectBuilder.Statistic.match().build());
    }

    @Test
    public void getStatistic_nullType_throwsException() {
        expectedException.expect(NullPointerException.class);

        StatisticCollection collection = ObjectBuilder.Statistic.collection().build();
        collection.getStatistic(null);
    }

    @Test
    public void getStatistic_nonExistentStatistic_throwsException() {
        expectedException.expect(IllegalArgumentException.class);

        StatisticCollection collection = ObjectBuilder.Statistic.collection().build();
        collection.getStatistic(RatingStatistic.class);
    }

}
