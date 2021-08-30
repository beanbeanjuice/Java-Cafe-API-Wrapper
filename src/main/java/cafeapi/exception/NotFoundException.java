package cafeapi.exception;

import cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link cafeapi.requests.Request Request} responds with a status code of 404.
 *
 * @author beanbeanjuice
 */
public class NotFoundException extends CafeException {

    /**
     * Creates a new {@link NotFoundException}.
     * @param request The {@link Request} that threw the {@link CafeException}.
     */
    public NotFoundException(@NotNull Request request) {
        super(request);
    }

}
