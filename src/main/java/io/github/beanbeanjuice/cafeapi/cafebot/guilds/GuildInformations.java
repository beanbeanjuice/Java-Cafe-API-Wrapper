package io.github.beanbeanjuice.cafeapi.cafebot.guilds;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.api.*;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for {@link GuildInformations} requests to the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class GuildInformations implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link GuildInformations} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public GuildInformations(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link GuildInformation} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String guildID} with a value of {@link GuildInformation guildInformation}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, GuildInformation> getAllGuildInformation()
    throws AuthorizationException, ResponseException {
        HashMap<String, GuildInformation> guilds = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/guilds")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode guild : request.getData().get("guilds")) {
            String guildID = guild.get("guild_id").asText();
            guilds.put(guildID, parseGuildInformation(guild));
        }

        return guilds;
    }

    /**
     * Retrieves the {@link GuildInformation} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @return The {@link GuildInformation} associated with the {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown then the specified {@link String guildID} does not exist in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     */
    @NotNull
    public GuildInformation getGuildInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return parseGuildInformation(request.getData().get("guild"));
    }

    /**
     * Creates a new {@link GuildInformation} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} to create.
     * @return True, if the {@link GuildInformations} was successfully created for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the specified {@link String guildID} already exists in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     */
    @NotNull
    public Boolean createGuildInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException, ConflictException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Updates the {@link GuildInformation} for a specified {@link String guildID}.
     * @param guildID The specified {@link String guildID}.
     * @param type The {@link GuildInformationType type} of value.
     * @param value The {@link String value} to update.
     * @return True, if the {@link GuildInformationType type} was successfully updated to the specified {@link String value} for the {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String guildID} was not found in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws TeaPotException Thrown when a variable is mismatched with its actual value. I.E. - Setting a boolean to "falfse" instead of "false"
     */
    @NotNull
    public Boolean updateGuildInformation(@NotNull String guildID, @NotNull GuildInformationType type, @NotNull Object value)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException, TeaPotException {

        switch (type){
            case PREFIX, MODERATOR_ROLE_ID, TWITCH_CHANNEL_ID,
                    MUTED_ROLE_ID, LIVE_NOTIFICATIONS_ROLE_ID, UPDATE_CHANNEL_ID,
                    COUNTING_CHANNEL_ID, POLL_CHANNEL_ID, RAFFLE_CHANNEL_ID,
                    BIRTHDAY_CHANNEL_ID, WELCOME_CHANNEL_ID, LOG_CHANNEL_ID,
                    VENTING_CHANNEL_ID, DAILY_CHANNEL_ID -> {
                if (!value.getClass().equals(String.class)) {
                    throw new TeaPotException(value + " is invalid for " + type);
                }
            }

            case NOTIFY_ON_UPDATE, AI_RESPONSE -> {
                if (!value.getClass().equals(Boolean.class)) {
                    throw new TeaPotException(value + " is invalid for " + type);
                }

                if ((Boolean) value) {
                    value = "1";
                } else {
                    value = "0";
                }
            }

        }

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/guilds/" + guildID)
                .addParameter("type", type.getType())
                .addParameter("value", value.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Deletes a {@link GuildInformation} for a specified {@link String guildID} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param guildID The specified {@link String guildID}.
     * @return True, if the {@link GuildInformation} was successfully deleted for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteGuildInformation(@NotNull String guildID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/guilds/" + guildID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Parses a {@link JsonNode} for its {@link GuildInformation}.
     * @param guild The {@link JsonNode} to parse.
     * @return The parsed {@link GuildInformation}.
     */
    @NotNull
    private GuildInformation parseGuildInformation(@NotNull JsonNode guild) {
        String prefix = guild.get("prefix").asText();
        String moderatorRoleID = guild.get("moderator_role_id").asText();
        String twitchChannelID = guild.get("twitch_channel_id").asText();
        String mutedRoleID = guild.get("muted_role_id").asText();
        String liveNotificationsRoleID = guild.get("live_notifications_role_id").asText();
        Boolean notifyOnUpdate = convertToBoolean(guild.get("notify_on_update").asText());
        String updateChannelID = guild.get("update_channel_id").asText();
        String countingChannelID = guild.get("counting_channel_id").asText();
        String pollChannelID = guild.get("poll_channel_id").asText();
        String raffleChannelID = guild.get("raffle_channel_id").asText();
        String birthdayChannelID = guild.get("birthday_channel_id").asText();
        String welcomeChannelID = guild.get("welcome_channel_id").asText();
        String logChannelID = guild.get("log_channel_id").asText();
        String ventingChannelID = guild.get("venting_channel_id").asText();
        Boolean aiResponseStatus = convertToBoolean(guild.get("ai_response").asText());
        String dailyChannelID = guild.get("daily_channel_id").asText();

        return new GuildInformation(
                prefix, moderatorRoleID, twitchChannelID,
                mutedRoleID, liveNotificationsRoleID, notifyOnUpdate,
                updateChannelID, countingChannelID, pollChannelID,
                raffleChannelID, birthdayChannelID, welcomeChannelID,
                logChannelID, ventingChannelID, aiResponseStatus,
                dailyChannelID
        );
    }

    /**
     * Converts a specified {@link String} to a {@link Boolean}.
     * @param string The specified {@link String}.
     * @return The parsed {@link Boolean}. False, by default, if there was an error.
     */
    @NotNull
    private Boolean convertToBoolean(@NotNull String string) {
        return string.equalsIgnoreCase("true") || string.equalsIgnoreCase("1");
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
