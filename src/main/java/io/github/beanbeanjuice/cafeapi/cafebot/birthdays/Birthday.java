package io.github.beanbeanjuice.cafeapi.cafebot.birthdays;

import io.github.beanbeanjuice.cafeapi.exception.program.BirthdayOverfillException;
import io.github.beanbeanjuice.cafeapi.exception.program.InvalidTimeZoneException;
import io.github.beanbeanjuice.cafeapi.utility.Time;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

/**
 * A class used to house {@link Birthday}.
 *
 * @author beanbeanjuice
 */
public class Birthday {

    private final BirthdayMonth month;
    private final Integer day;
    private final TimeZone timeZone;
    private final Boolean alreadyMentioned;

    /**
     * Creates a new {@link Birthday} object.
     * @param month The {@link BirthdayMonth month} of the {@link Birthday}.
     * @param day The {@link Integer day} of the {@link Birthday}.
     * @param alreadyMentioned False, if the user's birthday has not been mentioned by cafeBot.
     */
    public Birthday(@NotNull BirthdayMonth month, @NotNull Integer day, @NotNull String timeZone,
                    @NotNull Boolean alreadyMentioned) throws InvalidTimeZoneException, BirthdayOverfillException {
        this.month = month;
        this.day = day;

        if (month.getDaysInMonth() < day)
            throw new BirthdayOverfillException("You specified more days than there are in this month!");

        if (!Time.isValidTimeZone(timeZone))
            throw new InvalidTimeZoneException("The timezone specified is not allowed!");

        this.timeZone = TimeZone.getTimeZone(timeZone);
        this.alreadyMentioned = alreadyMentioned;
    }

    /**
     * @return The {@link BirthdayMonth month} of the {@link Birthday}.
     */
    @NotNull
    public BirthdayMonth getMonth() {
        return month;
    }

    /**
     * @return The {@link Integer day} of the {@link Birthday}.
     */
    @NotNull
    public Integer getDay() {
        return day;
    }

    /**
     * @return False, if the user's birthday has not been mentioned by cafeBot.
     */
    @NotNull
    public Boolean alreadyMentioned() {
        return alreadyMentioned;
    }

    /**
     * @return The {@link Date} of the {@link Birthday} in {@link TimeZone UTC} time.
     * @throws ParseException Thrown when the {@link Birthday} was unable to be parsed.
     */
    @NotNull
    public Date getUTCDate() throws ParseException {
        return Time.getFullDate(month.getMonthNumber() + "-" + day + "-2020", timeZone);
    }

    /**
     * @return The {@link TimeZone} for the {@link Birthday}.
     */
    @NotNull
    public TimeZone getTimeZone() {
        return timeZone;
    }

}
