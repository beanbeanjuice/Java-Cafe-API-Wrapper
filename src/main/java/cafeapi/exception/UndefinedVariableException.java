package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link cafeapi.requests.Request Request} responds with a status code of 400.
 *
 * @author beanbeanjuice
 */
public class UndefinedVariableException extends CafeException {

    /**
     * Creates a new {@link UndefinedVariableException}.
     * @param statusCode The {@link Integer statusCode} of the {@link CafeException}.
     * @param message The {@link String message} for the {@link CafeException}.
     */
    public UndefinedVariableException(@NotNull Integer statusCode, @NotNull String message) {
        super(statusCode, message);
    }

}
