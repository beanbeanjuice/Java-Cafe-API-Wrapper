package io.github.beanbeanjuice.cafeapi.exception.api;

import io.github.beanbeanjuice.cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link Request} responds with a status code of 500.
 *
 * @author beanbeanjuice
 */
public class ResponseException extends CafeException {

    /**
     * Creates a new {@link ResponseException}.
     * @param request The {@link Request} that threw the {@link CafeException}.
     */
    public ResponseException(@NotNull Request request) {
        super(request);
    }

}
