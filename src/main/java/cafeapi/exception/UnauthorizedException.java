package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * An {@link RuntimeException} that occurs when there is a status code of 401.
 *
 * @author beanbeanjuice
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Creates a new {@link UnauthorizedException}.
     * @param statusCode The {@link Integer status code} for the {@link UnauthorizedException}.
     * @param message The {@link String message} for the {@link UnauthorizedException}.
     */
    public UnauthorizedException(@NotNull Integer statusCode, @NotNull String message) {
        super("Error " + statusCode + ": " + message);
    }

}
