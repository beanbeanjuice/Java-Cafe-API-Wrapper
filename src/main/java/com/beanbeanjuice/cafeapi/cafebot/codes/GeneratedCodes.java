package com.beanbeanjuice.cafeapi.cafebot.codes;

import com.beanbeanjuice.cafeapi.CafeAPI;
import com.beanbeanjuice.cafeapi.exception.api.*;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.beanbeanjuice.cafeapi.exception.api.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for handling {@link GeneratedCodes} {@link Request Requests} for the {@link CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class GeneratedCodes implements com.beanbeanjuice.cafeapi.api.CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link GeneratedCodes} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public GeneratedCodes(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link String generatedCode} from the {@link CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String userID} and values of {@link String generatedCode}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, String> getAllGeneratedCodes()
    throws AuthorizationException, ResponseException {
        HashMap<String, String> codes = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/codes")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode user : request.getData().get("users")) {
            codes.put(user.get("user_id").asText(), user.get("generated_code").asText());
        }

        return codes;
    }

    /**
     * Retrieves a {@link String generatedCode} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link String generatedCode} for the {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when a {@link String generatedCode} for the specified {@link String userID} does not exist.
     */
    @NotNull
    public String getUserGeneratedCode(@NotNull String userID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/codes/" + userID)
                .setAuthorization(apiKey)
                .build();

        return request.getData().get("generated_code").asText();
    }

    /**
     * Updates a {@link String generatedCode} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param newCode The new {@link String generatedCode}.
     * @return True, if the {@link String generatedCode} was successfully updated in the {@link CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when a {@link String generatedCode} does not exist for the specified {@link String userID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateUserGeneratedCode(@NotNull String userID, @NotNull String newCode)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/codes/" + userID)
                .addParameter("generated_code", newCode)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Creates a {@link String generatedCode} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param newCode The new {@link String generatedCode} to create for the {@link String userID}.
     * @return True, if the {@link String generatedCode} was successfully created for the {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the {@link String generatedCode} already exists for the specified {@link String userID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean createUserGeneratedCode(@NotNull String userID, @NotNull String newCode)
    throws AuthorizationException, ResponseException, ConflictException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/codes/" + userID)
                .addParameter("generated_code", newCode)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a {@link String generatedCode} from a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link String generatedCode} was successfully deleted.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteUserGeneratedCode(@NotNull String userID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/codes/" + userID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Updates the {@link String apiKey}.
     * @param apiKey The new {@link String apiKey}.
     */
    @Override
    public void updateAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }
}
