package com.beanbeanjuice.cafeapi.api;

import org.jetbrains.annotations.NotNull;

/**
 * An interface used for {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI} Requests.
 *
 * @author beanbeanjuice
 */
public interface CafeAPI {

    /**
     * Updates the {@link String apiKey}.
     * @param apiKey The new {@link String apiKey}.
     */
    void updateAPIKey(@NotNull String apiKey);

}
