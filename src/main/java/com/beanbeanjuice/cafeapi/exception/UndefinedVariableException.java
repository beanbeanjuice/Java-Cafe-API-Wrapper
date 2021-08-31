package com.beanbeanjuice.cafeapi.exception;

import com.beanbeanjuice.cafeapi.requests.Request;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link CafeException} used when a {@link com.beanbeanjuice.cafeapi.requests.Request Request} responds with a status code of 400.
 *
 * @author beanbeanjuice
 */
public class UndefinedVariableException extends CafeException {

    /**
     * Creates a new {@link UndefinedVariableException}.
     * @param request The {@link Request} that threw the {@link CafeException}.
     */
    public UndefinedVariableException(@NotNull Request request) {
        super(request);
    }

}
