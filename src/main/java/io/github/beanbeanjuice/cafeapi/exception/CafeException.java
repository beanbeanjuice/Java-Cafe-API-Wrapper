package io.github.beanbeanjuice.cafeapi.exception;

import io.github.beanbeanjuice.cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A generic {@link RuntimeException} used for {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI} exceptions.
 *
 * @author beanbeanjuice
 */
public class CafeException extends RuntimeException {

    private final Integer statusCode;
    private final String message;

    private final Request request;

    /**
     * Creates a new {@link CafeException}.
     * @param request The {@link Request} that threw the {@link Exception}.
     */
    public CafeException(@NotNull Request request) {
        super("Error " + request.getStatusCode() + ": " + request.getData().get("message").asText());

        this.statusCode = request.getStatusCode();
        this.message = request.getData().get("message").asText();
        this.request = request;
    }

    /**
     * Creates a new {@link CafeException}.
     * @param statusCode The {@link Integer statusCode} for the {@link Exception}.
     * @param message The {@link String message} for the {@link Exception}.
     */
    public CafeException(@NotNull Integer statusCode, @NotNull String message) {
        super("Error " + statusCode + ": " + message);

        this.statusCode = statusCode;
        this.message = message;
        this.request = null;
    }

    /**
     * @return The {@link Integer statusCode} from the {@link Request} that threw the {@link CafeException}.
     */
    @NotNull
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @return The {@link String message} from the {@link Request} that threw the {@link CafeException}.
     */
    @NotNull
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return The {@link Request} that threw the {@link CafeException}.
     */
    @Nullable
    public Request getRequest() {
        return request;
    }
}
