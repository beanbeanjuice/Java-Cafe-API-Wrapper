package cafeapi.cafebot.birthdays;

import cafeapi.api.CafeAPI;
import cafeapi.exception.*;
import cafeapi.generic.Generic;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestRoute;
import cafeapi.requests.RequestType;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

/**
 * A class used for {@link Birthday} requests to the {@link cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Birthdays implements CafeAPI {

    private String apiKey;

    /**
     * Creates a new {@link Birthdays} object.
     * @param apiKey The {@link String apiKey} used for authorization.
     */
    public Birthdays(@NotNull String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Retrieves all {@link Birthday} from the {@link cafeapi.CafeAPI CafeAPI}.
     * @return A {@link HashMap} with keys of {@link String userID} and values of {@link Birthday}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public HashMap<String, Birthday> getAllBirthdays()
    throws AuthorizationException, ResponseException {
        HashMap<String, Birthday> birthdays = new HashMap<>();

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/birthdays")
                .setAuthorization(apiKey)
                .build();

        for (JsonNode birthday : request.getData().get("birthdays")) {
            String userID = birthday.get("user_id").asText();
            birthdays.put(userID, parseBirthday(birthday));
        }

        return birthdays;
    }

    /**
     * Retrieves the {@link Birthday} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return The {@link Birthday} for the specified {@link String userID}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link Birthday} for the specified {@link String userID} does not exist.
     */
    @NotNull
    public Birthday getUserBirthday(@NotNull String userID)
    throws AuthorizationException, ResponseException, NotFoundException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.GET)
                .setRoute("/birthdays/" + userID)
                .setAuthorization(apiKey)
                .build();

        return parseBirthday(request.getData().get("birthday"));
    }

    /**
     * Updates the {@link Birthday} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param month The {@link BirthdayMonth month} of the {@link Birthday}.
     * @param day The {@link Integer day} in the {@link BirthdayMonth month} of the {@link Birthday}.
     * @return True, if the {@link Birthday} was successfully updated in the {@link cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link Birthday} for the specified {@link String userID} does not exist.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws TeaPotException Thrown when the {@link BirthdayMonth month} or {@link Integer day} is invalid.
     */
    @NotNull
    public Boolean updateUserBirthday(@NotNull String userID, @NotNull BirthdayMonth month, @NotNull Integer day)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException, TeaPotException {

        // Checking for days in a month.
        if (month.getDaysInMonth() < day) {
            throw new TeaPotException("There are only " + month.getDaysInMonth() + " days in " + month + ", not " + day + ".");
        }

        // Checking if the month is valid.
        if (month == BirthdayMonth.ERROR) {
            throw new TeaPotException(month + " is an invalid month.");
        }

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/birthdays/" + userID)
                .addParameter("birthday", getBirthdayString(month, day))
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Updates the {@link Boolean alreadyMentioned} state for a {@link Birthday}.
     * @param userID The specified {@link String userID}.
     * @param alreadyMentioned The new {@link Boolean alreadyMentioned} state.
     * @return True, if the {@link Birthday} was successfully updated in the {@link cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws NotFoundException Thrown when the {@link Birthday} does not exist for the specified {@link String userID}.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     */
    @NotNull
    public Boolean updateUserBirthdayMention(@NotNull String userID, @NotNull Boolean alreadyMentioned)
    throws AuthorizationException, ResponseException, NotFoundException, UndefinedVariableException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.PATCH)
                .setRoute("/birthdays/" + userID + "/mention")
                .addParameter("already_mentioned", alreadyMentioned.toString())
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Creates a {@link Birthday} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @param month The {@link BirthdayMonth} for the {@link Birthday}.
     * @param day The {@link Integer day} of the {@link BirthdayMonth month} for the {@link Birthday}.
     * @return True, if the {@link Birthday} was successfully created.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     * @throws ConflictException Thrown when the {@link Birthday} for the specified {@link String userID} already exists.
     * @throws UndefinedVariableException Thrown when a variable is undefined.
     * @throws TeaPotException Thrown when the {@link BirthdayMonth month} or {@link Integer day} is invalid.
     */
    @NotNull
    public Boolean createUserBirthday(@NotNull String userID, @NotNull BirthdayMonth month, @NotNull Integer day)
    throws AuthorizationException, ResponseException, ConflictException, UndefinedVariableException, TeaPotException{
        // Checking for days in a month.
        if (month.getDaysInMonth() < day) {
            throw new TeaPotException("There are only " + month.getDaysInMonth() + " days in " + month + ", not " + day + ".");
        }

        // Checking if the month is valid.
        if (month == BirthdayMonth.ERROR) {
            throw new TeaPotException(month + " is an invalid month.");
        }

        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.POST)
                .setRoute("/birthdays/" + userID)
                .addParameter("birthday", getBirthdayString(month, day))
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 201;
    }

    /**
     * Removes a {@link Birthday} for a specified {@link String userID}.
     * @param userID The specified {@link String userID}.
     * @return True, if the {@link Birthday} has been successfully removed from the {@link cafeapi.CafeAPI CafeAPI}.
     * @throws AuthorizationException Thrown when the {@link String apiKey} is invalid.
     * @throws ResponseException Thrown when there is a generic server-side {@link CafeException}.
     */
    @NotNull
    public Boolean removeUserBirthday(@NotNull String userID)
    throws AuthorizationException, ResponseException {
        Request request = new RequestBuilder(RequestRoute.CAFEBOT, RequestType.DELETE)
                .setRoute("/birthdays/" + userID)
                .setAuthorization(apiKey)
                .build();

        return request.getStatusCode() == 200;
    }

    /**
     * Parses a {@link Birthday} from a {@link JsonNode}.
     * @param birthday The {@link JsonNode} to parse.
     * @return The parsed {@link Birthday}.
     */
    @NotNull
    private Birthday parseBirthday(@NotNull JsonNode birthday) {
        Date date = Generic.parseDate(birthday.get("birth_date").asText());
        Boolean alreadyMentioned = birthday.get("already_mentioned").asBoolean();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        return new Birthday(getBirthdayMonth(month), day, alreadyMentioned);
    }

    /**
     * Retrieves the {@link BirthdayMonth} from the {@link Integer index}.
     * @param index The {@link Integer index} of the month. January is 0.
     * @return The {@link BirthdayMonth} from the {@link Integer index}.
     */
    @NotNull
    private BirthdayMonth getBirthdayMonth(@NotNull Integer index) {
        for (BirthdayMonth month : BirthdayMonth.values()) {
            if (month.getMonthNumber() == index) {
                return month;
            }
        }

        return BirthdayMonth.ERROR;
    }

    /**
     * Parses a number and adds a 0 to the {@link String}.
     * @param number The {@link Integer number} to parse.
     * @return The parsed {@link String number}. Double-digit numbers don't have a 0 added.
     */
    @NotNull
    private String parseNumber(@NotNull Integer number) {
        if (number <= 9) {
            return "0" + number;
        }

        return number.toString();
    }

    /**
     * Gets the birthday {@link String} to send to the {@link cafeapi.CafeAPI CafeAPI}.
     * @param month The {@link BirthdayMonth montH} of the {@link Birthday}.
     * @param day The {@link Integer day} in the {@link BirthdayMonth month} of the {@link Birthday}.
     * @return The birthday {@link String} to send to the {@link cafeapi.CafeAPI CafeAPI}.
     */
    @NotNull
    private String getBirthdayString(@NotNull BirthdayMonth month, @NotNull Integer day) {
        return "2020-" + parseNumber(month.getMonthNumber()) + "-" + parseNumber(day);
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
