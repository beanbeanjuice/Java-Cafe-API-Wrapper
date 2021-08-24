package cafeapi.generic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * A generic class used for general parsing and other various methods.
 *
 * @author beanbeanjuice
 */
public class Generic {

    /**
     * Parses a {@link Timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @param timestampString The {@link String timestampString} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return The parsed {@link Timestamp timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}. UTC Timezone.
     * Null if timestamp was incorrectly entered.
     */
    @Nullable
    public static Timestamp parseTimestamp(@NotNull String timestampString) throws IllegalArgumentException {
        timestampString = timestampString.replace("T", " ").replace("Z", "");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Sets the timezone to UTC.
            return new Timestamp(simpleDateFormat.parse(timestampString).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

}
