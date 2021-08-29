package cafeapi.cafebot.raffles;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

/**
 * A class used to hold {@link Raffles} retrieved from the {@link cafeapi.CafeAPI CafeAPI}.
 *
 * @author beanbeanjuice
 */
public class Raffle {

    private final String messageID;
    private final Timestamp endingTime;
    private final Integer winnerAmount;

    /**
     * Creates a new {@link Raffle} object.
     * @param messageID The {@link String messageID} of the {@link Raffle}.
     * @param endingTime The {@link Timestamp endingTime} of the {@link Raffle}. UTC timezone.
     * @param winnerAmount The {@link Integer winnerAmount} of the {@link Raffle}.
     */
    public Raffle(@NotNull String messageID, @NotNull Timestamp endingTime, @NotNull Integer winnerAmount) {
        this.messageID = messageID;
        this.endingTime = endingTime;
        this.winnerAmount = winnerAmount;
    }

    /**
     * @return The {@link String messageID} of the {@link Raffle}.
     */
    @NotNull
    public String getMessageID() {
        return messageID;
    }

    /**
     * @return The {@link Timestamp endingTime} of the {@link Raffle}. UTC timezone.
     */
    @NotNull
    public Timestamp getEndingTime() {
        return endingTime;
    }

    /**
     * @return The {@link Integer winnerAmount} of the {@link Raffle}.
     */
    @NotNull
    public Integer getWinnerAmount() {
        return winnerAmount;
    }
}
