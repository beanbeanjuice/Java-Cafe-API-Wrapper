package cafeapi.requests;

import org.jetbrains.annotations.NotNull;

public enum RequestRoute {

    CAFE("/cafe/api/v1"),
    CAFEBOT("/cafeBot/api/v1");

    private String route;

    RequestRoute(@NotNull String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
