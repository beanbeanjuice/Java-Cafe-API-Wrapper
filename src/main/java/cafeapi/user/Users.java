package cafeapi.user;

import cafeapi.UnauthorizedException;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Users {

    private String apiKey;

    public Users(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    public ArrayList<User> getUsers() throws UnauthorizedException {

        ArrayList<User> users = new ArrayList<>();

        Request request = new RequestBuilder(RequestType.GET)
                .setRoute("/users")
                .setAPIKey(apiKey)
                .build();

        if (request.getStatusCode() == 401) {
            throw new UnauthorizedException(request.getStatusCode(), request.getData().get("message").textValue());
        }

        for (JsonNode user : request.getData().get("users")) {
            Integer id = user.get("user_id").intValue();
            String username = user.get("username").textValue();
            UserType userType = UserType.valueOf(user.get("user_type").textValue());
            users.add(new User(id, username, userType));
        }
        return users;
    }

}
