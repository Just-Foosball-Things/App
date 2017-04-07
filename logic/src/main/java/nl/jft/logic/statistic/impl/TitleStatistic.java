package nl.jft.logic.statistic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.jft.logic.participant.Title;
import nl.jft.logic.statistic.AbstractStatistic;

/**
 * A {@code TitleStatistic} is a {@link nl.jft.logic.statistic.Statistic} which keeps track of {@link Title titles}.
 *
 * @author Lesley
 */
public class TitleStatistic extends AbstractStatistic<Title> {

    private final List<Title> titles = new ArrayList<>();

    /**
     * Adds a {@link Title} to this {@code TitleStatistic}.
     *
     * @param title The {@code Title} to add, should not be {@code null}.
     */
    public void addTitle(Title title) {
        Objects.requireNonNull(title);

        synchronized (titles) {
            titles.add(title);
        }
    }

    /**
     * Removes a {@link Title} from this {@code TitleStatistic}, given that it was added in the first place.
     *
     * @param title The {@code Title} to remove, should not be {@code null}.
     */
    public void removeTitle(Title title) {
        Objects.requireNonNull(title);

        synchronized (titles) {
            titles.remove(title);
        }
    }

    @Override
    public List<Title> getValues() {
        List<Title> list = new ArrayList<>();

        synchronized (titles) {
            list.addAll(titles);
        }

        return list;
    }

}
