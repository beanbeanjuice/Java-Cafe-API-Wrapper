package io.github.beanbeanjuice.cafeapi.cafebot.beancoins.users;

import io.github.beanbeanjuice.cafeapi.api.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.api.*;
import io.github.beanbeanjuice.cafeapi.generic.CafeGeneric;
import io.github.beanbeanjuice.cafeapi.requests.Request;
import io.github.beanbeanjuice.cafeapi.requests.RequestBuilder;
import io.github.beanbeanjuice.cafeapi.requests.RequestRoute;
import io.github.beanbeanjuice.cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * A class used to make {@link DonationUsers} requests to the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class DonationUsers implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link DonationUsers} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public DonationUsers(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link Timestamp} from the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI} containing when a specified {@link String userID} can be donated to again.
     * @return A {@link HashMap} with keys of {@link String userID} and values of {@link Timestamp timeUntilNextDonation}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, Timestamp> getAllUserDonationTimes()
    throws AuthorizationException, ResponseException {
        HashMap<String, Timestamp> donationUsers = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/beanCoin/donation_users")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode user : request.getData().get("users")) {
            String userID = user.get("user_id").asText();
            Timestamp timeUntilNextDonation = CafeGeneric.parseTimestampFromAPI(user.get("time_until_next_donation").asText());

            donationUsers.put(userID, timeUntilNextDonation);
        }

        return donationUsers;
    }

    /**
     * Retrieves the {@link Timestamp timeUntilNextDonation} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Timestamp timeUntilNextDonation} for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link Timestamp timeUntilNextDonation} does not exist for the specified {@link String userID}.
     */
    @Nullable
    public Timestamp getUserDonationTime(@NotNull String userID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/beanCoin/donation_users/" + userID)
                .setAuthorization(apiKey)
                .build();

        return CafeGeneric.parseTimestampFromAPI(request.getData().get("user").get("time_until_next_donation").asText());
    }

    /**
     * Creates a new {@link Timestamp timeUntilNextDonation} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param timeUntilNextDonation The new {@link Timestamp timeUntilNextDonation}.
     * @return True, if the {@link Timestamp timeUntilNextDonation} was successfully created in the {@link io.github.beanbeanjuice.cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the {@link Timestamp timeUntilNextDonation} already exists for the specified {@link String userID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean addDonationUser(@NotNull String userID, @NotNull Timestamp timeUntilNextDonation)
    throws AuthorizationException, ResponseException, ConflictException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/beanCoin/donation_users/" + userID)
                .addParameter("time_stamp", timeUntilNextDonation.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Deletes a {@link Timestamp timeUntilNextDonation} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Timestamp timeUntilNextDonation} was successfully deleted for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean deleteDonationUser(@NotNull String userID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/beanCoin/donation_users/" + userID)
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
