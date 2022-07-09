package io.github.beanbeanjuice.cafeapi.exception.program;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link RuntimeException} that deals with incorrect days in a {@link io.github.beanbeanjuice.cafeapi.cafebot.birthdays.BirthdayMonth BirthdayMonth}.
 *
 * @author beanbeanjuice
 */
public class BirthdayOverfillException extends RuntimeException {

    /**
     * Creates a new {@link BirthdayOverfillException}.
     * @param message The message to send to the {@link RuntimeException}.
     */
    public BirthdayOverfillException(@NotNull String message) {
        super(message);
    }

    /**
     * Creates a new {@link BirthdayOverfillException}.
     */
    public BirthdayOverfillException() {
        super("More days than there are days in the month.");
    }

}
