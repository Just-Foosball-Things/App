package nl.jft.common.util;

/**
 * {@code Numbers} is an utility class for primitive number types.
 *
 * @author Oscar de Leeuw
 */
public final class Numbers {

    private static final double DELTA = 0.000001d;

    private Numbers() {
        throw new UnsupportedOperationException("Constructor should not be called.");
    }

    /**
     * Checks whether two given {@code doubles} are equal.
     * The {@code doubles} are considered equal if the absolute difference between the two {@code doubles} is less than a given delta.
     * <p>
     * Example: {@code boolean result = checkEqual(20d, 20.1d, 0.2d);}
     *
     * @param x     The first {@code double} to check for equality.
     * @param y     The second {@code double} to check for equality.
     * @param delta The allowed difference between the two compared values for them to be considered equal.
     * @return True when the two values are considered equal, false when they are not.
     */
    public static boolean checkEqual(double x, double y, double delta) {
        return Double.compare(x, y) == 0 || Math.abs(x - y) <= delta;
    }

    /**
     * Calls {@link #checkEqual(double, double, double)} with the constant {@link #DELTA}.
     *
     * @param x The first {@code double} to check for equality.
     * @param y The second {@code double} to check for equality.
     * @return True when the two values are considered equal, false when they are not.
     */
    public static boolean checkEqual(double x, double y) {
        return checkEqual(x, y, DELTA);
    }
}
