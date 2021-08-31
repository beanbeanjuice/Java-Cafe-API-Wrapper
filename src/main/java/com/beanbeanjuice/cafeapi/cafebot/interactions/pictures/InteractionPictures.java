package com.beanbeanjuice.cafeapi.cafebot.interactions.pictures;

import com.beanbeanjuice.cafeapi.api.CafeAPI;
import com.beanbeanjuice.cafeapi.cafebot.interactions.InteractionType;
import com.beanbeanjuice.cafeapi.exception.AuthorizationException;
import com.beanbeanjuice.cafeapi.exception.ResponseException;
import com.beanbeanjuice.cafeapi.exception.TeaPotException;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

/**
 * A class used to retrieve random pictures from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class InteractionPictures implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link InteractionPictures} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public InteractionPictures(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves a random {@link String interactionURL} for a specified {@link InteractionType}.
     * @param type The {@link InteractionType} specified.
     * @return The {@link String url} to the {@link com.beanbeanjuice.cafeapi.cafebot.interactions.users.Interaction Interaction} image.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws TeaPotException Thrown when an invalid {@link InteractionType} is entered.
     */
    @NotNull
    public String getRandomInteractionPicture(@NotNull InteractionType type)
    throws AuthorizationException, ResponseException, TeaPotException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/interaction_pictures/" + type)
                .setAuthorization(apiKey)
                .build();

        return request.getData().get("url").asText();
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
