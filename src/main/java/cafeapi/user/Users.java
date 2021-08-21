package cafeapi.user;

import cafeapi.api.CafeAPI;
import cafeapi.exception.ResponseException;
import cafeapi.exception.AuthorizationException;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestRoute;
import cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class used for everything to do with {@link Users} in the API.
 *
 * @author beanbeanjuice
 */
public class Users implements CafeAPI {

    private String apiKey; // TODO: Make something for updating the API key

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

    public User getUser(@NotNull String username) throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFE, RequestType.GET)
                .setRoute("/user/" + username)
                .setAuthorization(apiKey)
                .build();

        Integer ID = request.getData().get("user").get("user_id").intValue();
        UserType userType = UserType.valueOf(request.getData().get("user").get("user_type").textValue());
        return new User(ID, username, userType);
    }

    public Boolean deleteUser(@NotNull String username) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestRoute.CAFE, RequestType.DELETE)
                .setRoute("/user/" + username)
                .setAuthorization(apiKey);
        return requestBuilder.build().getStatusCode() == 200;
    }

    @Override
    public void updateAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }
}
