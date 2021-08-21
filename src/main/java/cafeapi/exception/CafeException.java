package cafeapi.exception;

import org.jetbrains.annotations.NotNull;

/**
 * A generic {@link RuntimeException} used for {@link cafeapi.CafeAPI CafeAPI} exceptions.
 *
 * @author beanbeanjuice
 */
public class CafeException extends RuntimeException {

    private final Integer statusCode;
    private final String message;

    /**
     * Creates a new {@link CafeException}.
     * @param statusCode The {@link Integer statusCode} of the {@link RuntimeException}.
     * @param message The {@link String message} of the {@link RuntimeException}.
     */
    public CafeException(@NotNull Integer statusCode, @NotNull String message) {
        super("Error " + statusCode + ": " + message);

        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * @return The {@link Integer statusCode} from the {@link cafeapi.requests.Request Request} that threw the {@link CafeException}.
     */
    @NotNull
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @return The {@link String message} from the {@link cafeapi.requests.Request Request} that threw the {@link CafeException}.
     */
    @NotNull
    @Override
    public String getMessage() {
        return message;
    }
}
