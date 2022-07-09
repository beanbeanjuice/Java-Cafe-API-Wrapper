package io.github.beanbeanjuice.cafeapi.exception.program;

import org.jetbrains.annotations.NotNull;

public class InvalidTimeZoneException extends RuntimeException {

    public InvalidTimeZoneException(@NotNull String message) {
        super(message);
    }

    public InvalidTimeZoneException() {
        super("The timezone is invalid!");
    }

}
