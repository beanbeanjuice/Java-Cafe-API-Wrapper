package io.github.beanbeanjuice.cafeapi.exception.program;

import org.jetbrains.annotations.NotNull;

public class BirthdayOverfillException extends RuntimeException {

    public BirthdayOverfillException(@NotNull String message) {
        super(message);
    }

    public BirthdayOverfillException() {
        super("More days than there are days in the month.");
    }

}
