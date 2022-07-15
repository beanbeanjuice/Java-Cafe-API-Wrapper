package com.beanbeanjuice.cafeapi.exception.api;

import com.beanbeanjuice.cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link Request} responds with a status code of 409.
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
