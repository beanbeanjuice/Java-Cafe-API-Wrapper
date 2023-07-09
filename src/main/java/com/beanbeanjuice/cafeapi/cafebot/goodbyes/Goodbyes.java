package com.beanbeanjuice.cafeapi.cafebot.goodbyes;

import com.beanbeanjuice.cafeapi.CafeAPI;
import com.beanbeanjuice.cafeapi.exception.api.*;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class used for the {@link Goodbyes} API.
 */
public class Goodbyes {

    private String apiKey;

    /**
     * Creates a new class used for {@link Goodbyes} API requests.
     * @param apiKey The API key used for authorization.
     */
    public Goodbyes(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves an {@link ArrayList} of {@link GuildGoodbye} containing all Guild Goodbyes in the {@link CafeAPI CafeAPI}.
     * @return The {@link ArrayList} of {@link GuildGoodbye}.
     * @throws AuthorizationException Thrown when the api key is unauthorized.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     */
    @NotNull
    public ArrayList<GuildGoodbye> getAllGuildGoodbyes() throws AuthorizationException, ResponseException {
        ArrayList<GuildGoodbye> guildGoodbyes = new ArrayList<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/goodbyes")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode guildGoodbye : request.getData().get("goodbyes")) {
            guildGoodbyes.add(parseGuildGoodbye(guildGoodbye));
        }

        return guildGoodbyes;
    }

    /**
     * Retrieves a {@link GuildGoodbye} from the {@link CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} to retrieve the {@link GuildGoodbye} for.
     * @return The {@link GuildGoodbye} retrieved.
     * @throws AuthorizationException Thrown when the API key is invalid.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     * @throws NotFoundException Thrown when the guild ID is not found.
     */
    @NotNull
    public GuildGoodbye getGuildGoodbye(@NotNull String guildID)
            throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/goodbyes/" + guildID)
                .setAuthorization(apiKey)
                .build();

        JsonNode guildGoodbye = request.getData().get("goodbye");

        return parseGuildGoodbye(guildGoodbye);
    }

    /**
     * Updates a {@link GuildGoodbye} in the {@link CafeAPI CafeAPI}.
     * @param guildGoodbye The new {@link GuildGoodbye}.
     * @return True, if updating the {@link GuildGoodbye} was successful.
     * @throws AuthorizationException Thrown when the API key is invalid.
     * @throws NotFoundException Thrown when the guild ID is not found.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     */
    @NotNull
    public Boolean updateGuildGoodbye(@NotNull GuildGoodbye guildGoodbye)
            throws AuthorizationException, NotFoundException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/goodbyes/" + guildGoodbye.getGuildID())
                .addParameter("description", guildGoodbye.getDescription())
                .addParameter("thumbnail_url", guildGoodbye.getThumbnailURL())
                .addParameter("image_url", guildGoodbye.getImageURL())
                .addParameter("message", guildGoodbye.getMessage())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Creates a new {@link GuildGoodbye} for the {@link CafeAPI CafeAPI}.
     * @param guildGoodbye The new {@link GuildGoodbye} to add.
     * @return True if the {@link GuildGoodbye} was successfully added.
     * @throws AuthorizationException Thrown when the API key is invalid.
     * @throws ConflictException Thrown when the provided guild ID already exists.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean createGuildGoodbye(@NotNull GuildGoodbye guildGoodbye)
            throws AuthorizationException, ConflictException, ResponseException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/goodbyes/" + guildGoodbye.getGuildID())
                .addParameter("description", guildGoodbye.getDescription())
                .addParameter("thumbnail_url", guildGoodbye.getThumbnailURL())
                .addParameter("image_url", guildGoodbye.getImageURL())
                .addParameter("message", guildGoodbye.getMessage())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a {@link GuildGoodbye} from the {@link CafeAPI CafeAPI}.
     * @param guildID The {@link String} ID of the {@link GuildGoodbye} to delete.
     * @return True if successfully deleted.
     * @throws AuthorizationException Thrown when the API key is invalid.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     */
    @NotNull
    public Boolean deleteGuildGoodbye(@NotNull String guildID)
            throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/goodbyes/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Parses a {@link GuildGoodbye} from the {@link JsonNode}.
     * @param node The {@link JsonNode} to parse.
     * @return The parsed {@link GuildGoodbye}.
     */
    @NotNull
    private GuildGoodbye parseGuildGoodbye(@NotNull JsonNode node) {
        String guildID = node.get("guild_id").asText();

        String description = node.get("description").asText();
        String thumbnailURL = node.get("thumbnail_url").asText();
        String imageURL = node.get("image_url").asText();
        String message = node.get("message").asText();

        if (description.equals("null")) {
            description = null;
        }

        if (thumbnailURL.equals("null")) {
            thumbnailURL = null;
        }

        if (imageURL.equals("null")) {
            imageURL = null;
        }

        if (message.equals("null")) {
            message = null;
        }

        return new GuildGoodbye(
                guildID,
                description,
                thumbnailURL,
                imageURL,
                message
        );
    }

}
