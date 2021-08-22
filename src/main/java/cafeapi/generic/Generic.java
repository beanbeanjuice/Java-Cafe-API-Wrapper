package cafeapi.generic;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

/**
 * A generic class used for general parsing and other various methods.
 *
 * @author beanbeanjuice
 */
public class Generic {

    /**
     * Parses a {@link Timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @param timestampString The {@link String timestampString} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return The parsed {@link Timestamp timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     */
    @NotNull
    public static Timestamp parseTimestamp(@NotNull String timestampString) throws IllegalArgumentException {
        return Timestamp.valueOf(timestampString.replace("T", " ").replace(".000Z", ""));
    }

}
