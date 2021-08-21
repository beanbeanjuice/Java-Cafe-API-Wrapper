package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link cafeapi.requests.Request Request} responds with a status code of 404.
 *
 * @author beanbeanjuice
 */
public class NotFoundException extends CafeException {

    /**
     * Creates a new {@link NotFoundException}.
     * @param statusCode The {@link Integer statusCode} for the {@link CafeException}.
     * @param message The {@link String message} for the {@link CafeException}.
     */
    public NotFoundException(@NotNull Integer statusCode, @NotNull String message) {
        super(statusCode, message);
    }

}
