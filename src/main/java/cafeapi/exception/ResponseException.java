package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * A generic {@link RuntimeException} that occurs when a status code of 500 is returned.
 *
 * @author beanbeanjuice
 */
public class ResponseException extends RuntimeException {

    /**
     * Creates a new {@link ResponseException}.
     * @param statusCode The {@link Integer status code} for the {@link ResponseException}.
     * @param message The {@link String message} for the {@link ResponseException}.
     */
    public ResponseException(@NotNull Integer statusCode, @NotNull String message) {
        super("Error " + statusCode + ": " + message);
    }

}
