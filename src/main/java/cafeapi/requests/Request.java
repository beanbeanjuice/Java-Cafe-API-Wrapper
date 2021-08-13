package cafeapi.requests;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

public class Request {

    private Integer statusCode;
    private JsonNode data;

    public Request(@NotNull Integer statusCode, @NotNull JsonNode data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    @NotNull
    public Integer getStatusCode() {
        return statusCode;
    }

    @NotNull
    public JsonNode getData() {
        return data;
    }
}
