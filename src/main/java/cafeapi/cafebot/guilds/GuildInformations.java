package cafeapi.cafebot.guilds;

import cafeapi.api.CafeAPI;
import cafeapi.exception.*;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestRoute;
import cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for {@link GuildInformations} requests to the {@link cafeapi.CafeAPI CafeAPI}.
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
     * Retrieves all {@link GuildInformation} from the {@link cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown then the specified {@link String guildID} does not exist in the {@link cafeapi.CafeAPI CafeAPI}.
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
     * Creates a new {@link GuildInformation} in the {@link cafeapi.CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} to create.
     * @return True, if the {@link GuildInformations} was successfully created for the specified {@link String guildID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the specified {@link String guildID} already exists in the {@link cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown when the specified {@link String guildID} was not found in the {@link cafeapi.CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws TeaPotException Thrown when a variable is mismatched with its actual value. I.E. - Setting a boolean to "falfse" instead of "false"
     */
    @NotNull
    public Boolean updateGuildInformation(@NotNull String guildID, @NotNull GuildInformationType type, @NotNull String value)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException, TeaPotException {

        if (type.equals(GuildInformationType.AI_RESPONSE) || type.equals(GuildInformationType.NOTIFY_ON_UPDATE)) {
            if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("0")) {
                value = "0";
            } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1")) {
                value = "1";
            } else {
                throw new TeaPotException("I'm A Tea Pot - The server cannot brew a cup of tea with a coffee maker.");
            }
        }

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/guilds/" + guildID)
                .addParameter("type", type.getType())
                .addParameter("value", value)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Deletes a {@link GuildInformation} for a specified {@link String guildID} in the {@link cafeapi.CafeAPI CafeAPI}.
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
