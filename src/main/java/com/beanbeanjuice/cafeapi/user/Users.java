package com.beanbeanjuice.cafeapi.user;

import com.beanbeanjuice.cafeapi.api.CafeAPI;
import com.beanbeanjuice.cafeapi.exception.ResponseException;
import com.beanbeanjuice.cafeapi.exception.AuthorizationException;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class used for everything to do with {@link Users} in the API.
 *
 * @author beanbeanjuice
 */
public class Users implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Users}.
     * @param apiKey The {@link String API key} used for making a {@link Request}.
     */
    public Users(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return The {@link ArrayList} of {@link User users} in the API database.
     * @throws AuthorizationException Thrown when the logged in account does not have access to view all users.
     */
    public ArrayList<User> getUsers() throws AuthorizationException, ResponseException {
        ArrayList<User> users = new ArrayList<>();

        Request request = new RequestBuilder(RequestRoute.CAFE, RequestType.GET)
                .setRoute("/users")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode user : request.getData().get("users")) {
            Integer id = user.get("user_id").intValue();
            String username = user.get("username").textValue();
            UserType userType = UserType.valueOf(user.get("user_type").textValue());
            users.add(new User(id, username, userType));
        }
        return users;
    }

    /**
     * Signup a {@link User}.
     * @param username The {@link String username} of the {@link User}.
     * @param password The {@link String password} of the {@link User}.
     * @return True, if successfully signed up.
     */
    public Boolean signUp(@NotNull String username, @NotNull String password) {
        Request request = new RequestBuilder(RequestRoute.CAFE, RequestType.POST)
                .setRoute("/user/signup")
                .addParameter("username", username)
                .addParameter("password", password)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Retrieves a {@link User} from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param username The {@link String username} of the {@link User}.
     * @return The specified {@link User}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link com.beanbeanjuice.cafeapi.exception.CafeException CafeException}.
     */
    public User getUser(@NotNull String username) throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFE, RequestType.GET)
                .setRoute("/user/" + username)
                .setAuthorization(apiKey)
                .build();

        Integer ID = request.getData().get("user").get("user_id").intValue();
        UserType userType = UserType.valueOf(request.getData().get("user").get("user_type").textValue());
        return new User(ID, username, userType);
    }

    /**
     * Deletes a {@link User} from the {@link com.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @param username The {@link String username} of the {@link User}.
     * @return True, if the {@link User} was successfully deleted.
     */
    public Boolean deleteUser(@NotNull String username) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestRoute.CAFE, RequestType.DELETE)
                .setRoute("/user/" + username)
                .setAuthorization(apiKey);
        return requestBuilder.build().getStatusCode() == 200;
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
