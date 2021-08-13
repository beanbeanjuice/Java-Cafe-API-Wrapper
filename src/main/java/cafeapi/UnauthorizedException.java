package cafeapi;

import org.jetbrains.annotations.NotNull;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(@NotNull Integer statusCode, @NotNull String message) {
        super("Error " + statusCode + ": " + message);
    }
}
