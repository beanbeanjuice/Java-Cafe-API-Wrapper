package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link cafeapi.requests.Request Request} responds with a status code of 401.
 *
 * @author beanbeanjuice
 */
public class UnauthorizedException extends CafeException {

    /**
     * Creates a new {@link UnauthorizedException}.
     * @param statusCode The {@link Integer statusCode} for the {@link CafeException}.
     * @param message The {@link String message} for the {@link CafeException}.
     */
    public UnauthorizedException(@NotNull Integer statusCode, @NotNull String message) {
        super(statusCode, message);
    }

}
