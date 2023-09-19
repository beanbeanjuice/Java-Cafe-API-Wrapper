package com.beanbeanjuice.cafeapi.cafebot.guilds;

import org.jetbrains.annotations.NotNull;

/**
 * A class used for {@link GuildInformation}.
 *
 * @author beanbeanjuice
 */
public class GuildInformation {

    private final String prefix;
    private final String moderatorRoleID;
    private final String twitchChannelID;
    private final String mutedRoleID;
    private final String liveNotificationsRoleID;
    private final Boolean notifyOnUpdate;
    private final String updateChannelID;
    private final String countingChannelID;
    private final String pollChannelID;
    private final String raffleChannelID;
    private final String birthdayChannelID;
    private final String welcomeChannelID;
    private final String goodbyeChannelID;
    private final String logChannelID;
    private final String ventingChannelID;
    private final Boolean aiResponseStatus;
    private final String dailyChannelID;

    /**
     * Creates a new {@link GuildInformation} object.
     * @param prefix The {@link String prefix} for the {@link String guildID}.
     * @param moderatorRoleID The {@link String moderatorRoleID} for the {@link String guildID}.
     * @param twitchChannelID The {@link String twitchChannelID} for the {@link String guildID}.
     * @param mutedRoleID The {@link String mutedRoleID} for the {@link String guildID}.
     * @param liveNotificationsRoleID The {@link String liveNotificationsRoleID} for the {@link String guildID}.
     * @param notifyOnUpdate The {@link Boolean notifyOnUpdate} for the {@link String guildID}.
     * @param updateChannelID The {@link String updateChannelID} for the {@link String guildID}.
     * @param countingChannelID The {@link String countingChannelID} for the {@link String guildID}.
     * @param pollChannelID The {@link String pollChannelID} for the {@link String guildID}.
     * @param raffleChannelID The {@link String raffleChannelID} for the {@link String guildID}.
     * @param birthdayChannelID The {@link String birthdayChannelID} for the {@link String guildID}.
     * @param welcomeChannelID The {@link String welcomeChannelID} for the {@link String guildID}.
     * @param goodbyeChannelID The {@link String goodbyeChannelID} for the {@link String guildID}.
     * @param logChannelID The {@link String logChannelID} for the {@link String guildID}.
     * @param ventingChannelID The {@link String ventingChannelID} for the {@link String guildID}.
     * @param aiResponseStatus The {@link Boolean aiResponseStatus} for the {@link String guildID}.
     * @param dailyChannelID The {@link String dailyChannelID} for the {@link String guildID}.
     */
    public GuildInformation(@NotNull String prefix, @NotNull String moderatorRoleID, @NotNull String twitchChannelID,
                            @NotNull String mutedRoleID, @NotNull String liveNotificationsRoleID, @NotNull Boolean notifyOnUpdate,
                            @NotNull String updateChannelID, @NotNull String countingChannelID, @NotNull String pollChannelID,
                            @NotNull String raffleChannelID, @NotNull String birthdayChannelID, @NotNull String welcomeChannelID,
                            @NotNull String goodbyeChannelID, @NotNull String logChannelID, @NotNull String ventingChannelID,
                            @NotNull Boolean aiResponseStatus, @NotNull String dailyChannelID) {
        this.prefix = prefix;
        this.moderatorRoleID = moderatorRoleID;
        this.twitchChannelID = twitchChannelID;
        this.mutedRoleID = mutedRoleID;
        this.liveNotificationsRoleID = liveNotificationsRoleID;
        this.notifyOnUpdate = notifyOnUpdate;
        this.updateChannelID = updateChannelID;
        this.countingChannelID = countingChannelID;
        this.pollChannelID = pollChannelID;
        this.raffleChannelID = raffleChannelID;
        this.birthdayChannelID = birthdayChannelID;
        this.welcomeChannelID = welcomeChannelID;
        this.goodbyeChannelID = goodbyeChannelID;
        this.logChannelID = logChannelID;
        this.ventingChannelID = ventingChannelID;
        this.aiResponseStatus = aiResponseStatus;
        this.dailyChannelID = dailyChannelID;
    }

    /**
     * @return The {@link String prefix} for the {@link GuildInformation}.
     */
    @NotNull
    public String getPrefix() {
        return prefix;
    }

    /**
     * @return The {@link String moderatorRoleID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getModeratorRoleID() {
        return moderatorRoleID;
    }

    /**
     * @return The {@link String twitchChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getTwitchChannelID() {
        return twitchChannelID;
    }

    /**
     * @return The {@link String mutedRoleID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getMutedRoleID() {
        return mutedRoleID;
    }

    /**
     * @return The {@link String liveNotificationsRoleID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getLiveNotificationsRoleID() {
        return liveNotificationsRoleID;
    }

    /**
     * @return The {@link Boolean notifyOnUpdate} for the {@link GuildInformation}.
     */
    @NotNull
    public Boolean getNotifyOnUpdate() {
        return notifyOnUpdate;
    }

    /**
     * @return The {@link String updateChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getUpdateChannelID() {
        return updateChannelID;
    }

    /**
     * @return The {@link String countingChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getCountingChannelID() {
        return countingChannelID;
    }

    /**
     * @return The {@link String pollChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getPollChannelID() {
        return pollChannelID;
    }

    /**
     * @return The {@link String raffleChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getRaffleChannelID() {
        return raffleChannelID;
    }

    /**
     * @return The {@link String birthdayChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getBirthdayChannelID() {
        return birthdayChannelID;
    }

    /**
     * @return The {@link String welcomeChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getWelcomeChannelID() {
        return welcomeChannelID;
    }

    /**
     * @return The {@link String goodbyeChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getGoodbyeChannelID() {
        return goodbyeChannelID;
    }

    /**
     * @return The {@link String logChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getLogChannelID() {
        return logChannelID;
    }

    /**
     * @return The {@link String ventingChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getVentingChannelID() {
        return ventingChannelID;
    }

    /**
     * @return The {@link Boolean aiResponseStatus} for the {@link GuildInformation}.
     */
    @NotNull
    public Boolean getAiResponseStatus() {
        return aiResponseStatus;
    }

    /**
     * @return The {@link String dailyChannelID} for the {@link GuildInformation}.
     */
    @NotNull
    public String getDailyChannelID() {
        return dailyChannelID;
    }
}
