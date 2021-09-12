package io.github.beanbeanjuice.cafeapi.cafebot.version;

import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.AuthorizationException;
import io.github.beanbeanjuice.cafeapi.exception.ResponseException;
import io.github.beanbeanjuice.cafeapi.exception.TeaPotException;
import io.github.beanbeanjuice.cafeapi.exception.UndefinedVariableException;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

/**
 * A class used for handling CafeBot {@link Versions} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Versions implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Versions} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public Versions(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves the current {@link String botVersion}.
     * @return The current {@link String botVersion} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    @NotNull
    public String getCurrentCafeBotVersion()
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/cafeBot")
                .setAuthorization(apiKey)
                .build();

        return  request.getData().get("bot_information").get("version").asText();
    }

    /**
     * Updates the current {@link String botVersion} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param versionNumber The {@link String versionNumber} to update it to.
     * @return True, if the {@link String versionNumber} was successfully updated.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link io.github.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws TeaPotException Thrown when you forget to add "v" to the beginning of the version number.
     */
    @NotNull
    public Boolean updateCurrentCafeBotVersion(@NotNull String versionNumber)
    throws AuthorizationException, ResponseException, UndefinedVariableException, TeaPotException {
        if (!versionNumber.startsWith("v")) {
            throw new TeaPotException("Version Number Must Start with 'v'.");
        }

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/cafeBot")
                .addParameter("version", versionNumber)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
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
