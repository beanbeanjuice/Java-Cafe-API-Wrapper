package cafeapi.user;

import cafeapi.exception.UnauthorizedException;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class used for everything to do with {@link Users} in the API.
 *
 * @author beanbeanjuice
 */
public class Users {

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
     * @throws UnauthorizedException Thrown when the logged in account does not have access to view all users.
     */
    public ArrayList<User> getUsers() throws UnauthorizedException {
        ArrayList<User> users = new ArrayList<>();

        Request request = new RequestBuilder(RequestType.GET)
                .setRoute("/users")
                .setAPIKey(apiKey)
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
        Request request = new RequestBuilder(RequestType.POST)
                .setRoute("/user/signup")
                .addParameter("username", username)
                .addParameter("password", password)
                .build();

        return request.getStatusCode() == 201;
    }

    public User getUser(@NotNull Integer userID) {
        Request request = new RequestBuilder(RequestType.GET)
                .setRoute("/user/" + userID)
                .setAPIKey(apiKey)
                .build();

        Integer ID = request.getData().get("user").get(0).get("user_id").intValue();
        String username = request.getData().get("user").get(0).get("username").textValue();
        UserType userType = UserType.valueOf(request.getData().get("user").get(0).get("user_type").textValue());
        return new User(ID, username, userType);
    }

    public Boolean deleteUser(@NotNull Integer userID) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestType.DELETE)
                .setRoute("/user/" + userID + "/delete")
                .setAPIKey(apiKey);
        return requestBuilder.build().getStatusCode() == 200;
    }

}
