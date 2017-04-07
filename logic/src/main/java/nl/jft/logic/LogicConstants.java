package nl.jft.logic;

import java.util.Date;

/**
 * @author Lesley
 */
public final class LogicConstants {

    public static final int INTERNAL_ID = -1;

    public static final Date INTERNAL_DATETIME = new Date(Long.MIN_VALUE);

    private LogicConstants() {
        throw new UnsupportedOperationException();
    }

}
