package nl.jft.logic.statistic;

import java.io.Serializable;
import java.util.List;

/**
 * A {@code Statistic} keeps track of a collection of {@code objects}.
 *
 * @param <T> The underlying {@code Object} that this {@code Statistic} keeps track of.
 * @author Lesley
 */
public interface Statistic<T> extends Serializable {

    /**
     * Gets the underlying collection of tracked {@code objects}.
     *
     * @return A {@code List} containing all tracked {@code objects}.
     */
    List<T> getValues();

    /**
     * Identifies this {@code Statistic}.
     *
     * @return A {@code Class} that identifies this {@code Statistic}.
     */
    Class<? extends Statistic> getType();

}
