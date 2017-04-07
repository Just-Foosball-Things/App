package nl.jft.logic.statistic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.jft.common.rating.Rating;
import nl.jft.logic.statistic.AbstractStatistic;

/**
 * An {@code RatingStatistic} is a {@link nl.jft.logic.statistic.Statistic} which keeps track of {@link Rating ratings}.
 *
 * @author Lesley
 */
public class RatingStatistic extends AbstractStatistic<Rating> {

    private final List<Rating> ratings = new ArrayList<>();

    /**
     * Adds a {@link Rating} to this {@code RatingStatistic}.
     *
     * @param rating The {@code Rating} to add, should not be {@code null}.
     */
    public void addRating(Rating rating) {
        Objects.requireNonNull(rating);

        synchronized (ratings) {
            ratings.add(rating);
        }
    }

    /**
     * Removes a {@link Rating} from this {@code RatingStatistic}, given that it was added in the first place.
     *
     * @param rating The {@code Rating} to remove, should not be {@code null}.
     */
    public void removeRating(Rating rating) {
        Objects.requireNonNull(rating);

        synchronized (ratings) {
            ratings.remove(rating);
        }
    }

    @Override
    public List<Rating> getValues() {
        List<Rating> list = new ArrayList<>();

        synchronized (ratings) {
            list.addAll(ratings);
        }

        return list;
    }

}
