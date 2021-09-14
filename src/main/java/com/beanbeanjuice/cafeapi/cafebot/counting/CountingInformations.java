package com.beanbeanjuice.cafeapi.cafebot.counting;

import com.beanbeanjuice.cafeapi.api.CafeAPI;
import com.beanbeanjuice.cafeapi.exception.*;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for {@link CountingInformation} requests for the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class CountingInformations implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link CountingInformations object}.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public CountingInformations(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link CountingInformation} in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String userID} and values of {@link CountingInformation guildCountingInformation}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, CountingInformation> getAllCountingInformation()
    throws AuthorizationException, ResponseException {
        HashMap<String, CountingInformation> guilds = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/counting/guilds")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode guild : request.getData().get("guilds")) {
            String guildID = guild.get("guild_id").asText();

            guilds.put(guildID, parseCountingInformation(guild));
        }

        return guilds;
    }

    /**
     * Retrieves the {@link CountingInformation} for a specified {@link String guildID}.
     * @param guildID THe specified {@link String guildID}.
     * @return The {@link CountingInformation} for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link CountingInformation} does not exist for the specified {@link String guildID}.
     */
    @NotNull
    public CountingInformation getGuildCountingInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/counting/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return parseCountingInformation(request.getData().get("counting_information"));
    }

    /**
     * Updates the {@link CountingInformation} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @param highestNumber The new {@link Integer highestNumber}.
     * @param lastNumber The new {@link Integer lastNumber}.
     * @param lastUserID The new {@link String lastuserID}.
     * @param failureRoleID The new {@link String failureRoleID}.
     * @return True, if the {@link CountingInformation} was successfully updated for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link CountingInformation} was not found for the specified {@link String guildID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateGuildCountingInformation(@NotNull String guildID, @NotNull Integer highestNumber, @NotNull Integer lastNumber,
                                                  @NotNull String lastUserID, @NotNull String failureRoleID)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/counting/guilds/" + guildID)
                .addParameter("highest_number", highestNumber.toString())
                .addParameter("last_number", lastNumber.toString())
                .addParameter("last_user_id", lastUserID)
                .addParameter("failure_role_id", failureRoleID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Updated the {@link CountingInformation} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @param countingInformation The new {@link CountingInformation}.
     * @return True, if the {@link CountingInformation} was successfully updated for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link CountingInformation} was not found for the specified {@link String guildID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateGuildCountingInformation(@NotNull String guildID, @NotNull CountingInformation countingInformation)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        return updateGuildCountingInformation(guildID, countingInformation.getHighestNumber(), countingInformation.getLastNumber(),
                countingInformation.getLastUserID(), countingInformation.getFailureRoleID());
    }

    /**
     * Creates a new {@link CountingInformation} for the specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @return True, if the {@link CountingInformation} was successfully created for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the {@link CountingInformation} already exists for the specified {@link String guildID}.
     */
    @NotNull
    public Boolean createGuildCountingInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException, ConflictException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/counting/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes the {@link CountingInformation} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @return True, if the {@link CountingInformation} was successfully deleted for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteGuildCountingInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/counting/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Parses a {@link JsonNode} for its {@link CountingInformation}.
     * @param node The {@link JsonNode node} to parse.
     * @return The parsed {@link CountingInformation}.
     */
    @NotNull
    private CountingInformation parseCountingInformation(@NotNull JsonNode node) {
        Integer highestNumber = node.get("highest_number").asInt();
        Integer lastNumber = node.get("last_number").asInt();
        String lastUserID = node.get("last_user_id").asText();
        String failureRoleID = node.get("failure_role_id").asText();

        return new CountingInformation(
                highestNumber,
                lastNumber,
                lastUserID,
                failureRoleID
        );
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
