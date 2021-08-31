package com.beanbeanjuice.cafeapi.cafebot.interactions;

import com.beanbeanjuice.cafeapi.api.CafeAPI;
import com.beanbeanjuice.cafeapi.cafebot.interactions.users.Interaction;
import com.beanbeanjuice.cafeapi.exception.*;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A class used for {@link Interactions} requests in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Interactions implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Interactions} API module.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public Interactions(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link Interaction} senders found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * Retrieves all {@link Interaction} sent found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Interaction} sent for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown when the {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws ConflictException Thrown when the specified {@link String userID} already exists in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * Retrieves all {@link Interaction} receivers found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * Retrieves all {@link Interaction} received found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Interaction} received for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown when the {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws NotFoundException Thrown when the specified {@link String userID} is not found in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @throws ConflictException Thrown when the specified {@link String userID} already exists in the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
        Integer hugAmount = jsonNode.get("hug_amount").asInt();
        Integer punchAmount = jsonNode.get("punch_amount").asInt();
        Integer kissAmount = jsonNode.get("kiss_amount").asInt();
        Integer biteAmount = jsonNode.get("bite_amount").asInt();
        Integer blushAmount = jsonNode.get("blush_amount").asInt();
        Integer cuddleAmount = jsonNode.get("cuddle_amount").asInt();
        Integer nomAmount = jsonNode.get("nom_amount").asInt();
        Integer pokeAmount = jsonNode.get("poke_amount").asInt();
        Integer slapAmount = jsonNode.get("slap_amount").asInt();
        Integer stabAmount = jsonNode.get("stab_amount").asInt();
        Integer hmphAmount = jsonNode.get("hmph_amount").asInt();
        Integer poutAmount = jsonNode.get("pout_amount").asInt();
        Integer throwAmount = jsonNode.get("throw_amount").asInt();
        Integer smileAmount = jsonNode.get("smile_amount").asInt();
        Integer stareAmount = jsonNode.get("stare_amount").asInt();
        Integer tickleAmount = jsonNode.get("tickle_amount").asInt();
        Integer rageAmount = jsonNode.get("rage_amount").asInt();
        Integer yellAmount = jsonNode.get("yell_amount").asInt();
        Integer headpatAmount = jsonNode.get("headpat_amount").asInt();
        Integer cryAmount = jsonNode.get("cry_amount").asInt();
        Integer danceAmount = jsonNode.get("dance_amount").asInt();
        Integer dabAmount = jsonNode.get("dab_amount").asInt();
        Integer bonkAmount = jsonNode.get("bonk_amount").asInt();
        Integer sleepAmount = jsonNode.get("sleep_amount").asInt();
        Integer dieAmount = jsonNode.get("die_amount").asInt();
        Integer welcomeAmount = jsonNode.get("welcome_amount").asInt();
        Integer lickAmount = jsonNode.get("lick_amount").asInt();
        Integer shushAmount = jsonNode.get("shush_amount").asInt();

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
                shushAmount
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
