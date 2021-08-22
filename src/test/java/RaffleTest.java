import cafeapi.CafeAPI;
import cafeapi.cafebot.raffles.Raffle;
import cafeapi.exception.ConflictException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RaffleTest {

    @Test
    @DisplayName("Test Raffles API")
    public void rafflesAPITest() {
        CafeAPI cafeAPI = new CafeAPI("beanbeanjuice", "password123");

        // Makes sure a raffle can be created.
        Assertions.assertTrue(() -> cafeAPI.raffles().createRaffle("798830792938881024", new Raffle(
                "878895791081676831",
                new Timestamp(System.currentTimeMillis()),
                3
        )));

        // Makes sure creating the same raffle throws an exception.
        Assertions.assertThrows(ConflictException.class, () -> cafeAPI.raffles().createRaffle("798830792938881024", new Raffle(
                "878895791081676831",
                new Timestamp(System.currentTimeMillis()),
                3
        )));

        // Makes sure that the newly created raffle shows up in the API.
        Assertions.assertTrue(() -> {
            ArrayList<Raffle> raffles = cafeAPI.raffles().getAllRaffles().get("798830792938881024");

            for (Raffle raffle : raffles) {
                if (raffle.getMessageID().equals("878895791081676831")) {
                    return true;
                }
            }

            return false;
        });

        // Makes sure that getting the raffle directly also works.
        Assertions.assertTrue(() -> {
            ArrayList<Raffle> raffles = cafeAPI.raffles().getGuildRaffles("798830792938881024");

            for (Raffle raffle : raffles) {
                if (raffle.getMessageID().equals("878895791081676831")) {
                    return true;
                }
            }

            return false;
        });

        // Makes sure deleting the raffle is able to happen.
        Assertions.assertTrue(cafeAPI.raffles().deleteRaffle("798830792938881024", "878895791081676831"));

        // Makes sure the newly created raffle that was deleted no longer shows up in the API.
        Assertions.assertFalse(() -> {
            ArrayList<Raffle> raffles = cafeAPI.raffles().getAllRaffles().get("798830792938881024");

            for (Raffle raffle : raffles) {
                if (raffle.getMessageID().equals("878895791081676831")) {
                    return true;
                }
            }

            return false;
        });
    }

}
