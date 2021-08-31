package com.beanbeanjuice.cafeapi.requests;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link Record} containing the request's {@link Integer status code} and resulting {@link JsonNode data}.
 *
 * @author beanbeanjuice
 */
public record Request(Integer statusCode, JsonNode data) {

    /**
     * Creates a new {@link Request} object.
     * @param statusCode The resulting {@link Integer status code}.
     * @param data The {@link JsonNode data} retrieved from the {@link Request}.
     */
    public Request(@NotNull Integer statusCode, @NotNull JsonNode data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    /**
     * @return The {@link Request Request's} {@link Integer status code}.
     */
    @NotNull
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @return The {@link Request Request's} {@link JsonNode data}.
     */
    @NotNull
    public JsonNode getData() {
        return data;
    }

}
