package com.beanbeanjuice.cafeapi.cafebot.welcomes;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * A class used for Welcome Information for a Discord Guild.
 *
 * @author beanbeanjuice
 */
public class GuildWelcome {

    @Getter private final String guildID;
    private final String description;
    private final String thumbnailURL;
    private final String imageURL;
    private final String message;

    /**
     * Creates a new {@link GuildWelcome} object.
     * @param guildID The {@link String guildID} of the {@link GuildWelcome}.
     * @param description The {@link String description} of the {@link GuildWelcome}.
     * @param thumbnailURL The {@link String thumbnailURL} of the {@link GuildWelcome}.
     * @param imageURL The {@link String imageURL} of the {@link GuildWelcome}.
     * @param message The {@link String message} of the {@link GuildWelcome}.
     */
    public GuildWelcome(String guildID, @Nullable String description, @Nullable String thumbnailURL,
                        @Nullable String imageURL, @Nullable String message) {
        this.guildID = guildID;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.imageURL = imageURL;
        this.message = message;
    }

    /**
     * @return The {@link String description} of the {@link GuildWelcome}.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * @return The {@link String thumbnailURL} of the {@link GuildWelcome}.
     */
    public Optional<String> getThumbnailURL() {
        return Optional.ofNullable(thumbnailURL);
    }

    /**
     * @return The {@link String imageURL} of the {@link GuildWelcome}.
     */
    public Optional<String> getImageURL() {
        return Optional.ofNullable(imageURL);
    }

    /**
     * @return The {@link String message} of the {@link GuildWelcome}.
     */
    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

}
