package cafeapi.api;

import org.jetbrains.annotations.NotNull;

public interface CafeAPI {

    /**
     * Updates the {@link String apiKey}.
     * @param apiKey The new {@link String apiKey}.
     */
    void updateAPIKey(@NotNull String apiKey);

}
