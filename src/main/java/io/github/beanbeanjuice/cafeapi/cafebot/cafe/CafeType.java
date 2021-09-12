package io.github.beanbeanjuice.cafeapi.cafebot.cafe;

import org.jetbrains.annotations.NotNull;

/**
 * A static {@link CafeType} used for the {@link CafeUsers} API.
 *
 * @author beanbeanjuice
 */
public enum CafeType {

    BEAN_COINS ("bean_coins"),
    LAST_SERVING_TIME ("last_serving_time"),
    ORDERS_BOUGHT ("orders_bought"),
    ORDERS_RECEIVED ("orders_received");

    private final String type;

    /**
     * Creates a new {@link CafeType} static object.
     * @param type The {@link String type} used for the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     */
    CafeType(@NotNull String type) {
        this.type = type;
    }

    /**
     * @return The {@link String type} used for {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI} {@link io.github.beanbeanjuice.cafeapi.requests.Request Requests}.
     */
    @NotNull
    public String getType() {
        return type;
    }
}
