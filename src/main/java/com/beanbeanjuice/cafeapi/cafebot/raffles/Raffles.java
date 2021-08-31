package com.beanbeanjuice.cafeapi.cafebot.raffles;

import com.beanbeanjuice.cafeapi.api.CafeAPI;
import com.beanbeanjuice.cafeapi.exception.AuthorizationException;
import com.beanbeanjuice.cafeapi.exception.ConflictException;
import com.beanbeanjuice.cafeapi.exception.ResponseException;
import com.beanbeanjuice.cafeapi.exception.UndefinedVariableException;
import com.beanbeanjuice.cafeapi.generic.CafeGeneric;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class used for requests about {@link Raffles} in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Raffles implements CafeAPI {

    private String apiKey;

    /**
     * Creates the {@link Raffles} module for the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param apiKey The authorization {@link String apiKey}.
     */
    public Raffles(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link Raffle Raffles} in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return The {@link HashMap} with keys of {@link String guildID} and a value of {@link ArrayList} containing {@link Raffle Raffles}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    @NotNull
    public HashMap<String, ArrayList<Raffle>> getAllRaffles()
    throws AuthorizationException, ResponseException {
        HashMap<String, ArrayList<Raffle>> raffles = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/raffles")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode raffle : request.getData().get("raffles")) {
            String guildID = raffle.get("guild_id").asText();
            String messageID = raffle.get("message_id").asText();
            Timestamp endingTime = CafeGeneric.parseTimestampFromAPI(raffle.get("ending_time").asText());
            Integer winnerAmount = raffle.get("winner_amount").asInt();

            if (!raffles.containsKey(guildID)) {
                raffles.put(guildID, new ArrayList<>());
            }

            raffles.get(guildID).add(new Raffle(messageID, endingTime, winnerAmount));
        }

        return raffles;
    }

    /**
     * Retrieves all {@link Raffle Raffles} from a specified Discord server.
     * @param guildID The {@link String guildID} of the Discord server.
     * @return The {@link ArrayList} of {@link Raffle} in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    @NotNull
    public ArrayList<Raffle> getGuildRaffles(@NotNull String guildID)
    throws AuthorizationException, ResponseException {
        ArrayList<Raffle> raffles = new ArrayList<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/raffles/" + guildID)
                .setAuthorization(apiKey)
                .build();

        for (JsonNode raffle : request.getData().get("raffles")) {
            String messageID = raffle.get("message_id").asText();
            Timestamp endingTime = CafeGeneric.parseTimestampFromAPI(raffle.get("ending_time").asText());
            Integer winnerAmount = raffle.get("winner_amount").asInt();

            raffles.add(new Raffle(messageID, endingTime, winnerAmount));
        }

        return raffles;
    }

    /**
     * Creates a new {@link Raffle} in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} of the {@link Raffle}.
     * @param raffle The {@link Raffle} to add to the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return True, if the {@link Raffle} was successfully created.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws ConflictException Thrown when the {@link Raffle} already exists for the specified {@link String guildID}.
     */
    @NotNull
    public Boolean createRaffle(@NotNull String guildID, @NotNull Raffle raffle)
    throws AuthorizationException, ResponseException, UndefinedVariableException, ConflictException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/raffles/" + guildID)
                .addParameter("message_id", raffle.getMessageID())
                .addParameter("ending_time", raffle.getEndingTime().toString())
                .addParameter("winner_amount", raffle.getWinnerAmount().toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a {@link Raffle} from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} of the {@link Raffle}.
     * @param raffle The {@link Raffle} to delete from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return True, if the {@link Raffle} was successfully removed from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean deleteRaffle(@NotNull String guildID, @NotNull Raffle raffle)
    throws AuthorizationException, ResponseException, UndefinedVariableException {
        return deleteRaffle(guildID, raffle.getMessageID());
    }

    /**
     * Deletes a {@link Raffle} from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param guildID The {@link String guildID} of the {@link Raffle}.
     * @param messageID The {@link String messageID} of the {@link Raffle}.
     * @return True, if the {@link Raffle} was successfully removed from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean deleteRaffle(@NotNull String guildID, @NotNull String messageID)
    throws AuthorizationException, ResponseException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/raffles/" + guildID)
                .addParameter("message_id", messageID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Updates the {@link String apiKey}.
     * @param apiKey The {@link String apiKey} to update the current one to.
     */
    @Override
    public void updateAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }
}
