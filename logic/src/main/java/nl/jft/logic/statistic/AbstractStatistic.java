package nl.jft.logic.statistic;

/**
 * Contains a default implementation of {@link Statistic}. It returns its own {@code class} as identifier.
 *
 * @author Lesley
 */
public abstract class AbstractStatistic<T> implements Statistic<T> {

    @Override
    public final Class<? extends Statistic> getType() {
        return getClass();
    }

}
