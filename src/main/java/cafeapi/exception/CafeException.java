package cafeapi.exception;

import cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A generic {@link RuntimeException} used for {@link cafeapi.CafeAPI CafeAPI} exceptions.
 *
 * @author beanbeanjuice
 */
public class CafeException extends RuntimeException {

    private final Integer statusCode;
    private final String message;

    private final Request request;

    public CafeException(@NotNull Request request) {
        super("Error " + request.getStatusCode() + ": " + request.getData().get("message").asText());

        this.statusCode = request.getStatusCode();
        this.message = request.getData().get("message").asText();
        this.request = request;
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

    /**
     * @return The {@link Request} that threw the {@link CafeException}.
     */
    public Request getRequest() {
        return request;
    }
}
