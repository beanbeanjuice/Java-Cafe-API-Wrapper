package io.github.beanbeanjuice.cafeapi.beta;

import io.github.beanbeanjuice.cafeapi.CafeAPI;
import io.github.beanbeanjuice.cafeapi.exception.api.ConflictException;
import io.github.beanbeanjuice.cafeapi.exception.api.NotFoundException;
import io.github.beanbeanjuice.cafeapi.generic.CafeGeneric;
import io.github.beanbeanjuice.cafeapi.requests.RequestLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class DonationUsersTest {

    @Test
    @DisplayName("Donation Users API Test")
    public void testDonationUsersAPI() {
        CafeAPI cafeAPI = new CafeAPI("beanbeanjuice", System.getenv("API_PASSWORD"), RequestLocation.BETA);

        Assertions.assertTrue(cafeAPI.DONATION_USER.deleteDonationUser("738590591767543921"));
        Assertions.assertThrows(NotFoundException.class, () -> cafeAPI.DONATION_USER.getUserDonationTime("738590591767543921"));
        Timestamp currentTimestamp = CafeGeneric.parseTimestamp((new Timestamp(System.currentTimeMillis())).toString());
        Assertions.assertTrue(cafeAPI.DONATION_USER.addDonationUser("738590591767543921", currentTimestamp));
        Assertions.assertThrows(ConflictException.class, () -> cafeAPI.DONATION_USER.addDonationUser("738590591767543921", currentTimestamp));
        Assertions.assertEquals(currentTimestamp, cafeAPI.DONATION_USER.getAllUserDonationTimes().get("738590591767543921"));
        Assertions.assertEquals(currentTimestamp, cafeAPI.DONATION_USER.getUserDonationTime("738590591767543921"));

        Assertions.assertTrue(cafeAPI.DONATION_USER.deleteDonationUser("738590591767543921"));
        Assertions.assertThrows(NotFoundException.class, () -> cafeAPI.DONATION_USER.getUserDonationTime("738590591767543921"));
    }

}
