package com.beanbeanjuice.cafeapi.cafebot.voicebinds;

import org.jetbrains.annotations.NotNull;

/**
 * A class used for a {@link VoiceChannelBind} for a Discord Server.
 *
 * @author beanbeanjuice
 */
public class VoiceChannelBind {

    private final String voiceChannelID;
    private final String roleID;

    /**
     * Creates a new {@link VoiceChannelBind}.
     * @param voiceChannelID The {@link String voiceChannelID} of the {@link VoiceChannelBind}.
     * @param roleID The {@link String roleID} of the {@link VoiceChannelBind}.
     */
    public VoiceChannelBind(@NotNull String voiceChannelID, @NotNull String roleID) {
        this.voiceChannelID = voiceChannelID;
        this.roleID = roleID;
    }

    /**
     * @return The {@link String voiceChannelID} for the {@link VoiceChannelBind}.
     */
    @NotNull
    public String getVoiceChannelID() {
        return voiceChannelID;
    }

    /**
     * @return The {@link String roleID} for the {@link VoiceChannelBind}.
     */
    @NotNull
    public String getRoleID() {
        return roleID;
    }
}
