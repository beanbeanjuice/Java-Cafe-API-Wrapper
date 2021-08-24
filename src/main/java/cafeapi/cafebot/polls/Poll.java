package cafeapi.cafebot.polls;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

/**
 * A class used for {@link Poll} data.
 *
 * @author beanbeanjuice
 */
public class Poll {

    private final String messageID;
    private final Timestamp endingTime;

    /**
     * Creates a new {@link Poll} object.
     * @param messageID The {@link String messageID} of the {@link Poll}.
     * @param endingTime The {@link Timestamp endingTime} of the {@link Poll}. UTC Timezone.
     */
    public Poll(@NotNull String messageID, @NotNull Timestamp endingTime) {
        this.messageID = messageID;
        this.endingTime = endingTime;
    }

    /**
     * @return The {@link String messageID} of the {@link Poll}.
     */
    @NotNull
    public String getMessageID() {
        return messageID;
    }

    /**
     * @return The {@link Timestamp endingTime} of the {@link Poll}. UTC Timezone.
     */
    @NotNull
    public Timestamp getEndingTime() {
        return endingTime;
    }
}
