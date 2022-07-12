package io.github.beanbeanjuice.cafeapi.cafebot.interactions.pictures;

import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.cafebot.interactions.InteractionType;
import io.github.beanbeanjuice.cafeapi.exception.api.AuthorizationException;
import io.github.beanbeanjuice.cafeapi.exception.api.ResponseException;
import io.github.beanbeanjuice.cafeapi.exception.api.TeaPotException;
import io.github.beanbeanjuice.cafeapi.exception.api.CafeException;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

/**
 * A class used to retrieve random pictures from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
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
     * @return The {@link String url} to the {@link io.github.beanbeanjuice.cafeapi.cafebot.interactions.users.Interaction Interaction} image.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException CafeException}.
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
