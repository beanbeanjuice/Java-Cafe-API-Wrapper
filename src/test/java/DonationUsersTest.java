import cafeapi.CafeAPI;
import cafeapi.exception.ConflictException;
import cafeapi.exception.NotFoundException;
import cafeapi.generic.CafeGeneric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class DonationUsersTest {

    @Test
    @DisplayName("Donation Users API Test")
    public void testDonationUsersAPI() {
        CafeAPI cafeAPI = new CafeAPI("beanbeanjuice", "password123");

        Assertions.assertTrue(cafeAPI.donationUsers().deleteDonationUser("738590591767543921"));
        Assertions.assertThrows(NotFoundException.class, () -> cafeAPI.donationUsers().getUserDonationTime("738590591767543921"));
        Timestamp currentTimestamp = CafeGeneric.parseTimestamp((new Timestamp(System.currentTimeMillis())).toString());
        Assertions.assertTrue(cafeAPI.donationUsers().addDonationUser("738590591767543921", currentTimestamp));
        Assertions.assertThrows(ConflictException.class, () -> cafeAPI.donationUsers().addDonationUser("738590591767543921", currentTimestamp));
        Assertions.assertEquals(currentTimestamp, cafeAPI.donationUsers().getAllUserDonationTimes().get("738590591767543921"));
        Assertions.assertEquals(currentTimestamp, cafeAPI.donationUsers().getUserDonationTime("738590591767543921"));

        Assertions.assertTrue(cafeAPI.donationUsers().deleteDonationUser("738590591767543921"));
        Assertions.assertThrows(NotFoundException.class, () -> cafeAPI.donationUsers().getUserDonationTime("738590591767543921"));
    }

}
