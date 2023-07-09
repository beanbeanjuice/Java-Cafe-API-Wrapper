package com.beanbeanjuice.cafeapi.cafebot.interactions;

import com.beanbeanjuice.cafeapi.CafeAPI;
import com.beanbeanjuice.cafeapi.cafebot.interactions.users.Interaction;
import com.beanbeanjuice.cafeapi.exception.api.*;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for {@link Interactions} requests in the {@link CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Interactions implements com.beanbeanjuice.cafeapi.api.CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Interactions} API module.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public Interactions(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link Interaction} senders found in the {@link CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String userID} and a value of {@link Interaction} sent.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, Interaction> getAllInteractionSenders()
    throws AuthorizationException, ResponseException {
        HashMap<String, Interaction> senders = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/senders")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode interactionSender : request.getData().get("interactions_sent")) {
            String userID = interactionSender.get("user_id").asText();
            senders.put(userID, parseInteraction(interactionSender));
        }

        return senders;
    }

    /**
     * Retrieves all {@link Interaction} sent found in the {@link CafeAPI CafeAPI} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Interaction} sent for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     */
    @NotNull
    public Interaction getUserInteractionsSent(@NotNull String userID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/senders/" + userID)
                .setAuthorization(apiKey)
                .build();

        return parseInteraction(request.getData().get("interactions_sent"));
    }

    /**
     * Retrieves a specific {@link InteractionType} sent from the specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param type The {@link InteractionType type} of {@link Interaction}.
     * @return The amount of the specified {@link InteractionType} that was sent from the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Integer getSpecificUserInteractionSentAmount(@NotNull String userID, @NotNull InteractionType type)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/senders/" + userID)
                .addParameter("type", type.toString().toLowerCase())
                .setAuthorization(apiKey)
                .build();

        return request.getData().get(type.getType()).asInt();
    }

    /**
     * Updates the {@link Interaction} sent amount of a specified {@link InteractionType} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param type The specified {@link InteractionType type}.
     * @param value The specified {@link Integer value} for the {@link InteractionType}.
     * @return True, if the {@link Interaction} was successfully updated for the {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateSpecificUserInteractionSentAmount(@NotNull String userID, @NotNull InteractionType type, @NotNull Integer value)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/interactions/senders/" + userID)
                .addParameter("type", type.toString().toLowerCase())
                .addParameter("value", value.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Creates a new {@link Interaction} sender.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Interaction} sender was successfully created.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the specified {@link String userID} already exists in the {@link CafeAPI CafeAPI}.
     */
    @NotNull
    public Boolean createUserInteractionsSent(@NotNull String userID)
    throws AuthorizationException, ResponseException, ConflictException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/interactions/senders/" + userID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a specified {@link Interaction} sender.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Interaction} sender was successfully deleted.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteUserInteractionsSent(@NotNull String userID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/interactions/senders/" + userID)
                .setAuthorization(apiKey)
                .build();

        return  request.getStatusCode() == 200;
    }

    // ==============================
    //     INTERACTION RECEIVERS
    // ==============================

    /**
     * Retrieves all {@link Interaction} receivers found in the {@link CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String userID} and a value of {@link Interaction} received.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, Interaction> getAllInteractionReceivers()
    throws AuthorizationException, ResponseException {
        HashMap<String, Interaction> receivers = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/receivers")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode interactionReceiver : request.getData().get("interactions_received")) {
            String userID = interactionReceiver.get("user_id").asText();
            receivers.put(userID, parseInteraction(interactionReceiver));
        }

        return receivers;
    }

    /**
     * Retrieves all {@link Interaction} received found in the {@link CafeAPI CafeAPI} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Interaction} received for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     */
    @NotNull
    public Interaction getUserInteractionsReceived(@NotNull String userID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/receivers/" + userID)
                .setAuthorization(apiKey)
                .build();

        return parseInteraction(request.getData().get("interactions_received"));
    }

    /**
     * Retrieves a specific {@link InteractionType} received for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param type The {@link InteractionType type} of {@link Interaction}.
     * @return The amount of the specified {@link InteractionType} that was received for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Integer getSpecificUserInteractionReceivedAmount(@NotNull String userID, @NotNull InteractionType type)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interactions/receivers/" + userID)
                .addParameter("type", type.toString().toLowerCase())
                .setAuthorization(apiKey)
                .build();

        return request.getData().get(type.getType()).asInt();
    }

    /**
     * Updates the {@link Interaction} received amount of a specified {@link InteractionType} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param type The specified {@link InteractionType type}.
     * @param value The specified {@link Integer value} for the {@link InteractionType}.
     * @return True, if the {@link Interaction} was successfully updated for the {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link CafeAPI CafeAPI}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateSpecificUserInteractionReceivedAmount(@NotNull String userID, @NotNull InteractionType type, @NotNull Integer value)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/interactions/receivers/" + userID)
                .addParameter("type", type.toString().toLowerCase())
                .addParameter("value", value.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Creates a new {@link Interaction} receiver.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Interaction} receiver was successfully created.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the specified {@link String userID} already exists in the {@link CafeAPI CafeAPI}.
     */
    @NotNull
    public Boolean createUserInteractionsReceived(@NotNull String userID)
    throws AuthorizationException, ResponseException, ConflictException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/interactions/receivers/" + userID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a specified {@link Interaction} receiver.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Interaction} receiver was successfully deleted.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteUserInteractionsReceived(@NotNull String userID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/interactions/receivers/" + userID)
                .setAuthorization(apiKey)
                .build();

        return  request.getStatusCode() == 200;
    }

    /**
     * Parses a {@link JsonNode} for the {@link Interaction}.
     * @param jsonNode The {@link JsonNode} to parse into an {@link Interaction}.
     * @return The parsed {@link Interaction}.
     */
    @NotNull
    private Interaction parseInteraction(@NotNull JsonNode jsonNode) {
        Integer hugAmount = jsonNode.get(InteractionType.HUG.getType()).asInt();
        Integer punchAmount = jsonNode.get(InteractionType.PUNCH.getType()).asInt();
        Integer kissAmount = jsonNode.get(InteractionType.KISS.getType()).asInt();
        Integer biteAmount = jsonNode.get(InteractionType.BITE.getType()).asInt();
        Integer blushAmount = jsonNode.get(InteractionType.BLUSH.getType()).asInt();
        Integer cuddleAmount = jsonNode.get(InteractionType.CUDDLE.getType()).asInt();
        Integer nomAmount = jsonNode.get(InteractionType.NOM.getType()).asInt();
        Integer pokeAmount = jsonNode.get(InteractionType.POKE.getType()).asInt();
        Integer slapAmount = jsonNode.get(InteractionType.SLAP.getType()).asInt();
        Integer stabAmount = jsonNode.get(InteractionType.STAB.getType()).asInt();
        Integer hmphAmount = jsonNode.get(InteractionType.HMPH.getType()).asInt();
        Integer poutAmount = jsonNode.get(InteractionType.POUT.getType()).asInt();
        Integer throwAmount = jsonNode.get(InteractionType.THROW.getType()).asInt();
        Integer smileAmount = jsonNode.get(InteractionType.SMILE.getType()).asInt();
        Integer stareAmount = jsonNode.get(InteractionType.STARE.getType()).asInt();
        Integer tickleAmount = jsonNode.get(InteractionType.TICKLE.getType()).asInt();
        Integer rageAmount = jsonNode.get(InteractionType.RAGE.getType()).asInt();
        Integer yellAmount = jsonNode.get(InteractionType.YELL.getType()).asInt();
        Integer headpatAmount = jsonNode.get(InteractionType.HEADPAT.getType()).asInt();
        Integer cryAmount = jsonNode.get(InteractionType.CRY.getType()).asInt();
        Integer danceAmount = jsonNode.get(InteractionType.DANCE.getType()).asInt();
        Integer dabAmount = jsonNode.get(InteractionType.DAB.getType()).asInt();
        Integer bonkAmount = jsonNode.get(InteractionType.BONK.getType()).asInt();
        Integer sleepAmount = jsonNode.get(InteractionType.SLEEP.getType()).asInt();
        Integer dieAmount = jsonNode.get(InteractionType.DIE.getType()).asInt();
        Integer welcomeAmount = jsonNode.get(InteractionType.WELCOME.getType()).asInt();
        Integer lickAmount = jsonNode.get(InteractionType.LICK.getType()).asInt();
        Integer shushAmount = jsonNode.get(InteractionType.SHUSH.getType()).asInt();
        Integer waveAmount = jsonNode.get(InteractionType.WAVE.getType()).asInt();
        Integer shootAmount = jsonNode.get(InteractionType.SHOOT.getType()).asInt();
        Integer amazedAmount = jsonNode.get(InteractionType.AMAZED.getType()).asInt();
        Integer askAmount = jsonNode.get(InteractionType.ASK.getType()).asInt();
        Integer boopAmount = jsonNode.get(InteractionType.BOOP.getType()).asInt();
        Integer loveAmount = jsonNode.get(InteractionType.LOVE.getType()).asInt();
        Integer nosebleedAmount = jsonNode.get(InteractionType.NOSEBLEED.getType()).asInt();
        Integer okAmount = jsonNode.get(InteractionType.OK.getType()).asInt();
        Integer uwuAmount = jsonNode.get(InteractionType.UWU.getType()).asInt();
        Integer winkAmount = jsonNode.get(InteractionType.WINK.getType()).asInt();

        return new Interaction(
                hugAmount, punchAmount, kissAmount,
                biteAmount, blushAmount, cuddleAmount,
                nomAmount, pokeAmount, slapAmount,
                stabAmount, hmphAmount, poutAmount,
                throwAmount, smileAmount, stareAmount,
                tickleAmount, rageAmount, yellAmount,
                headpatAmount, cryAmount, danceAmount,
                dabAmount, bonkAmount, sleepAmount,
                dieAmount, welcomeAmount, lickAmount,
                shushAmount, waveAmount, shootAmount,
                amazedAmount, askAmount, boopAmount,
                loveAmount, nosebleedAmount, okAmount,
                uwuAmount, winkAmount
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
