package nl.jft.logic.statistic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Keeps track of different {@link Statistic statistics}. The tracked {@code Statistics} can be retreived by using their respective {@code Type} as key.
 *
 * @author Lesley
 */
public class StatisticCollection implements Serializable {

    private final Map<Class<? extends Statistic>, Statistic<?>> statistics = new HashMap<>();

    /**
     * Adds a {@link Statistic} to this {@code StatisticCollection}.
     *
     * @param statistic The {@code Statistic} to add, should not be {@code null}.
     * @throws NullPointerException     If the given {@code Statistic} is {@code null}.
     * @throws IllegalArgumentException If the given {@code Statistic} is already tracked inside this {@code StatisticCollection}.
     */
    public void addStatistic(Statistic statistic) {
        Objects.requireNonNull(statistic);

        Class<? extends Statistic> type = statistic.getType();
        synchronized (statistics) {
            if (statistics.containsKey(type)) {
                throw new IllegalArgumentException(String.format("Statistic already tracked for type: %s.", type));
            }

            statistics.put(type, statistic);
        }
    }

    /**
     * Gets a tracked {@link Statistic} from this {@code StatisticCollection}.
     *
     * @param type The {@code Type} of {@code Statistic} to retrieve, should not be {@code null}.
     * @param <T>  The underlying {@code objects} that the {@code Statistic} keeps track of.
     * @return A {@code Statistic} containing information about underlying {@code objects}.
     * @throws NullPointerException     If the given {@code Type} is {@code null}.
     * @throws IllegalArgumentException If the given {@code Type} is not tracked inside this {@code StatisticCollection}.
     */
    public <T extends Statistic> T getStatistic(Class<T> type) {
        Objects.requireNonNull(type);

        synchronized (statistics) {
            Statistic<?> statistic = statistics.get(type);
            if (statistic == null) {
                throw new IllegalArgumentException(String.format("No statistic found for type: %s.", type));
            }

            return (T) statistic;
        }
    }

}
