package cafeapi.generic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * A generic class used for general parsing and other various methods.
 *
 * @author beanbeanjuice
 */
public class CafeGeneric {

    /**
     * Parses a {@link Timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @param timestampString The {@link String timestampString} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return The parsed {@link Timestamp timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}. UTC Timezone.
     * Null if timestamp was incorrectly entered.
     * @throws IllegalArgumentException - Thrown when the pattern given is invalid.
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

    /**
     * Parses a {@link Timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @param timestampString The {@link String timestampString} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return The parsed {@link Timestamp timestamp} retrieved from the {@link cafeapi.CafeAPI CafeAPI}. UTC Timezone.
     * Null if timestamp was incorrectly entered.
     * @throws IllegalArgumentException - Thrown when the pattern given is invalid.
     */
    @Nullable
    public static Timestamp parseTimestampFromAPI(@NotNull String timestampString) throws IllegalArgumentException {
        timestampString = timestampString.replace("T", " ").replace("Z", "");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(simpleDateFormat.parse(timestampString).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Parses a {@link Date} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @param dateString The {@link String dateString} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return The parsed {@link Date} retrieved from the {@link cafeapi.CafeAPI CafeAPI}. UTC Timezone.
     * Null if the date was incorrectly entered.
     * @throws IllegalArgumentException - Thrown when the pattern given is invalid.
     */
    @Nullable
    public static Date parseDateFromAPI(@NotNull String dateString) throws IllegalArgumentException {
        dateString = dateString.replace("T", " ").replace("Z", "");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return new Date(simpleDateFormat.parse(dateString).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Parses a {@link Boolean} into its number value.
     * @param bool The {@link Boolean} to parse.
     * @return "1", if true. If there was an error, it will be "0" by default.
     */
    @NotNull
    public static String parseBoolean(@NotNull Boolean bool) {
        if (bool.equals(true)) {
            return "1";
        }
        return "0";
    }

}
