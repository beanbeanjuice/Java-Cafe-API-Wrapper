package cafeapi;

import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestType;
import cafeapi.user.Users;
import org.jetbrains.annotations.NotNull;

public class CafeAPI {

    private String apiKey;
    private String userAgent;
    private final Boolean useCafeBot;
    public static final String url = "http://localhost:4101/cafe/api/v1";

    private Users users;

    public CafeAPI(@NotNull String username, @NotNull String password, @NotNull Boolean useCafeBot) {
        this.userAgent = username;
        this.useCafeBot = useCafeBot;

        try {
            apiKey = getToken(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        users = new Users(apiKey);
    }

    private String getToken(@NotNull String username, @NotNull String password) {
        Request request = new RequestBuilder(RequestType.POST)
                .setRoute("/user/login")
                .addParameter("username", username)
                .addParameter("password", password)
                .build();

        return request.getData().get("api_key").textValue();
    }

    public Users users() {
        return users;
    }

}
