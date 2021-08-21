package cafeapi.exception;

import cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link cafeapi.requests.Request Request} responds with a status code of 409.
 *
 * @author beanbeanjuice
 */
public class ConflictException extends CafeException {

    /**
     * Creates a new {@link ConflictException}.
     * @param request The {@link Request} that threw the {@link CafeException}.
     */
    public ConflictException(@NotNull Request request) {
        super(request);
    }

}
