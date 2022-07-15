package com.beanbeanjuice.cafeapi.requests;

import org.jetbrains.annotations.NotNull;

/**
 * A class used for {@link RequestRoute} objects.
 *
 * @author beanbeanjuice
 */
public enum RequestRoute {

    CAFE("/cafe/api/v1"),
    CAFEBOT("/cafeBot/api/v1");

    private final String route;

    /**
     * Creates a new {@link RequestRoute} static object.
     * @param route The {@link String route} of the {@link Request}.
     */
    RequestRoute(@NotNull String route) {
        this.route = route;
    }

    /**
     * @return The route {@link String url} for the {@link Request}.
     */
    @NotNull
    public String getRoute() {
        return route;
    }
}
