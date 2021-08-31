package com.beanbeanjuice.cafeapi.cafebot.birthdays;

import org.jetbrains.annotations.NotNull;

/**
 * A class used to house {@link Birthday}.
 *
 * @author beanbeanjuice
 */
public class Birthday {

    private final BirthdayMonth month;
    private final Integer day;
    private final Boolean alreadyMentioned;

    /**
     * Creates a new {@link Birthday} object.
     * @param month The {@link BirthdayMonth month} of the {@link Birthday}.
     * @param day The {@link Integer day} of the {@link Birthday}.
     * @param alreadyMentioned False, if the user's birthday has not been mentioned by cafeBot.
     */
    public Birthday(@NotNull BirthdayMonth month, @NotNull Integer day, @NotNull Boolean alreadyMentioned) {
        this.month = month;
        this.day = day;
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
}
