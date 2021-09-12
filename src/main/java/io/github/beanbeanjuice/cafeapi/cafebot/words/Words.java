package io.github.beanbeanjuice.cafeapi.cafebot.words;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.AuthorizationException;
import io.github.beanbeanjuice.cafeapi.exception.NotFoundException;
import io.github.beanbeanjuice.cafeapi.exception.ResponseException;
import io.github.beanbeanjuice.cafeapi.exception.UndefinedVariableException;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class used to retrieve {@link Words} from the database.
 *
 * @author beanbeanjuice
 */
public class Words implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Words} API.
     * @param apiKey The API key to use for connection and verification to the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     */
    public Words(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Gets an {@link ArrayList} of {@link Word} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @return The {@link ArrayList} of {@link Word} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     */
    @NotNull
    public ArrayList<Word> getAllWords() throws ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/words")
                .setAuthorization(apiKey)
                .build();

        ArrayList<Word> wordList = new ArrayList<>();

        for (JsonNode word : request.getData().get("words")) {
            wordList.add(new Word(word.get("word").asText(), word.get("uses").asInt()));
        }

        return wordList;
    }

    /**
     * Gets a {@link Word} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param word The {@link String word} to get.
     * @return The {@link Word} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws NotFoundException Thrown when the word is not found.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     */
    @NotNull
    public Word getWord(@NotNull String word) throws NotFoundException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/words/" + word)
                .setAuthorization(apiKey)
                .build();

        return new Word(word, request.getData().get("word").get("uses").asInt());
    }

    /**
     * Updates a {@link Word} in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param word The {@link String word} to be updated.
     * @param uses The new {@link Integer uses} to be set to.
     * @return True, if successful.
     * @throws NotFoundException Thrown when the word was not found.
     * @throws ResponseException Thrown when there is a generic server-side exception.
     * @throws AuthorizationException Thrown when the current API key is invalid.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateWord(@NotNull String word, @NotNull Integer uses)
    throws NotFoundException, ResponseException, AuthorizationException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/words/" + word)
                .addParameter("uses", uses.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    @Override
    public void updateAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }
}
