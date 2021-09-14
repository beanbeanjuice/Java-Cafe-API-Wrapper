package com.beanbeanjuice.cafeapi.cafebot.counting;

import org.jetbrains.annotations.NotNull;

/**
 * A class used for {@link CountingInformation}.
 *
 * @author beanbeanjuice
 */
public class CountingInformation {

    private final Integer highestNumber;
    private final Integer lastNumber;
    private final String lastUserID;
    private final String failureRoleID;

    /**
     * Creates a new {@link CountingInformation} object.
     * @param highestNumber The {@link Integer highestNumber}.
     * @param lastNumber The {@link Integer lastNumber}.
     * @param lastUserID The {@link String lastUserID}.
     */
    public CountingInformation(@NotNull Integer highestNumber, @NotNull Integer lastNumber, @NotNull String lastUserID,
                               @NotNull String failureRoleID) {
        this.highestNumber = highestNumber;
        this.lastNumber = lastNumber;
        this.lastUserID = lastUserID;
        this.failureRoleID = failureRoleID;
    }

    /**
     * @return The {@link Integer highestNumber}.
     */
    @NotNull
    public Integer getHighestNumber() {
        return highestNumber;
    }

    /**
     * @return The {@link Integer lastNumber}.
     */
    @NotNull
    public Integer getLastNumber() {
        return lastNumber;
    }

    /**
     * @return The {@link String lastUserID}.
     */
    @NotNull
    public String getLastUserID() {
        return lastUserID;
    }

    /**
     * @return The {@link String failureRoleID}.
     */
    @NotNull
    public String getFailureRoleID() {
        return failureRoleID;
    }

}
