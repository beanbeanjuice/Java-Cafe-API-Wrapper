package io.github.beanbeanjuice.cafeapi.utility;

import io.github.beanbeanjuice.cafeapi.cafebot.birthdays.BirthdayMonth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TimeTest {

    @Test
    @DisplayName("Testing UTC Time")
    public void testUTCTime() {
        Time time = new Time();
        String format = "MM-dd-yyyy HH:mm";

        time.setDefaultFormat(format);
        String[] instantTime = Instant.now().toString().split("T");

        assertEquals(instantTime[0], time.format("yyyy-MM-dd"));
        assertTrue(instantTime[1].startsWith(time.format("HH:mm")));
    }

    @Test
    @DisplayName("Testing Throw if Default Time is Null")
    public void testNullDefaultTime() {
        Time time = new Time();
        assertThrows(NullPointerException.class, time::format);
        time.setDefaultFormat("MM-dd-yyyy");
        assertDoesNotThrow(() -> { time.format(); });
    }

    @Test
    @DisplayName("Convert Timestamp to UTC")
    public void convertToUTC() {
        System.out.println(Time.convertBirthdayToUTC(BirthdayMonth.JANUARY, 3, TimeZone.getTimeZone("EST")));
    }

}
