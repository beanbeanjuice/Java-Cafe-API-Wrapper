package io.github.beanbeanjuice.cafeapi.cafebot.twitches;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.AuthorizationException;
import io.github.beanbeanjuice.cafeapi.exception.ConflictException;
import io.github.beanbeanjuice.cafeapi.exception.ResponseException;
import io.github.beanbeanjuice.cafeapi.exception.UndefinedVariableException;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class used for the Twitch API for the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class GuildTwitches implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link GuildTwitches} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public GuildTwitches(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all Twitch channels from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String guildID} and a value of {@link ArrayList} of {@link String twitchChannelName}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    @NotNull
    public HashMap<String, ArrayList<String>> getAllTwitches()
    throws AuthorizationException, ResponseException {
        HashMap<String, ArrayList<String>> twitches = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/guilds/twitch")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode guildTwitch : request.getData().get("guilds_twitch")) {
            String guildID = guildTwitch.get("guild_id").asText();
            String twitchChannel = guildTwitch.get("twitch_channel").asText();

            if (!twitches.containsKey(guildID)) {
                twitches.put(guildID, new ArrayList<>());
            }

            twitches.get(guildID).add(twitchChannel);
        }

        return twitches;
    }

    /**
     * Retrieves all Twitch {@link String twitchChannelName} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @return The {@link ArrayList} of {@link String twitchChannelName}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    @NotNull
    public ArrayList<String> getGuildTwitches(@NotNull String guildID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/guilds/twitch/" + guildID)
                .setAuthorization(apiKey)
                .build();

        try {
            return new ObjectMapper().readValue(request.getData().get("twitch_channels").toString(), ArrayList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Adds a {@link String twitchChannelName} to the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @param twitchChannelName The {@link String twitchChannelName} to add.
     * @return True, if the {@link String twitchChannelName} was successfully added.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws ConflictException Thrown when the {@link String twitchChannelName} already exists for the specified {@link String guildID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean addGuildTwitch(@NotNull String guildID, @NotNull String twitchChannelName)
    throws AuthorizationException, ResponseException, ConflictException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/guilds/twitch/" + guildID)
                .addParameter("twitch_channel", twitchChannelName)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Removes a {@link String twitchChannelName} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @param twitchChannelName The {@link String twitchChannelName} to add.
     * @return True, if the {@link String twitchChannelName} was successfully removed.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean removeGuildTwitch(@NotNull String guildID, @NotNull String twitchChannelName)
    throws AuthorizationException, ResponseException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/guilds/twitch/" + guildID)
                .addParameter("twitch_channel", twitchChannelName)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Updates the {@link String apikey}.
     * @param apiKey The new {@link String apiKey}.
     */
    @Override
    public void updateAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }
}
