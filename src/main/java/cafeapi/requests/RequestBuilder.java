package cafeapi.requests;

import cafeapi.CafeAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;

public class RequestBuilder {

    private String apiURL = "http://localhost:4101/cafe/api/v1";

    private RequestType requestType;
    private String route;
    private final HashMap<String, String> parameters;

    private HttpClient httpClient;
    private URIBuilder uriBuilder;
    private HttpResponse httpResponse;
    private String apiKey;
    private Header header;

    public RequestBuilder(@NotNull RequestType requestType) {
        this.requestType = requestType;
        parameters = new HashMap<>();
    }

    public RequestBuilder setRoute(@NotNull String route) {
        this.route = route;
        return this;
    }

    public RequestBuilder addParameter(@NotNull String key, @NotNull String value) {
        parameters.put(key, value);
        return this;
    }

    public RequestBuilder setAPIKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Request build() {

        try {
            httpClient = HttpClients.createDefault();
            uriBuilder = new URIBuilder(apiURL + route);
            parameters.forEach((key, value) -> {
                uriBuilder.setParameter(key, value);
            });
            header = new BasicHeader("Authorization", apiKey);

            switch (requestType) {
                case GET -> {
                    httpResponse = get();
                }

                case POST -> {
                    httpResponse = post();
                }

                case PATCH -> {

                }

                case DELETE -> {

                }
            }

            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            HttpEntity httpEntity = httpResponse.getEntity();
            try (InputStream inputStream = httpEntity.getContent()) {
                return new Request(statusCode, new ObjectMapper().readTree(inputStream));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpResponse post() throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader(header);
//        httpPost.addHeader("Authorization", apiKey);

        return httpClient.execute(httpPost);
    }

    private HttpResponse get() throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader(header);

        return httpClient.execute(httpGet);
    }


}
