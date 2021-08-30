package cafeapi.cafebot.cafe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

/**
 * A class used for {@link CafeUser} objects.
 *
 * @author beanbeanjuice
 */
public class CafeUser {

    private final String userID;
    private final Double beanCoins;
    private final Timestamp lastServingTime;
    private final Integer ordersBought;
    private final Integer ordersReceived;

    /**
     * Creates a new {@link CafeUser} object.
     * @param userID The {@link String userID}.
     * @param beanCoins The {@link Double beanCoins}.
     * @param lastServingTime The {@link Timestamp lastServingTime}.
     * @param ordersBought The {@link Integer ordersBought}.
     * @param ordersReceived The {@link Integer ordersReceived}.
     */
    public CafeUser(@NotNull String userID, @NotNull Double beanCoins, @Nullable Timestamp lastServingTime,
                    @NotNull Integer ordersBought, @NotNull Integer ordersReceived) {
        this.userID = userID;
        this.beanCoins = beanCoins;
        this.lastServingTime = lastServingTime;
        this.ordersBought = ordersBought;
        this.ordersReceived = ordersReceived;
    }

    /**
     * @return The {@link String lastUserID}.
     */
    @NotNull
    public String getUserID() {
        return userID;
    }

    /**
     * @return The {@link Double beanCoins}.
     */
    @NotNull
    public Double getBeanCoins() {
        return beanCoins;
    }

    /**
     * @return The {@link Timestamp lastServingTime}.
     */
    @Nullable
    public Timestamp getLastServingTime() {
        return lastServingTime;
    }

    /**
     * @return The {@link Integer ordersBought}.
     */
    @NotNull
    public Integer getOrdersBought() {
        return ordersBought;
    }

    /**
     * @return The {@link Integer ordersReceived}.
     */
    @NotNull
    public Integer getOrdersReceived() {
        return ordersReceived;
    }
}
