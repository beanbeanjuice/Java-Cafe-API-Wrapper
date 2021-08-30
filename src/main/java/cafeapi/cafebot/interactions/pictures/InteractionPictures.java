package cafeapi.cafebot.interactions.pictures;

import cafeapi.api.CafeAPI;
import cafeapi.cafebot.interactions.InteractionType;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestRoute;
import cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

public class InteractionPictures implements CafeAPI {

    private String apiKey;

    public InteractionPictures(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    @NotNull
    public String getInteractionPicture(@NotNull InteractionType type) {
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
