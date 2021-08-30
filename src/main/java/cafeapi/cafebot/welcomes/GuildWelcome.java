package cafeapi.cafebot.welcomes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class used for Welcome Information for a Discord Guild.
 *
 * @author beanbeanjuice
 */
public class GuildWelcome {

    private final String guildID;
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
    public GuildWelcome(@NotNull String guildID, @Nullable String description, @Nullable String thumbnailURL,
                        @Nullable String imageURL, @Nullable String message) {
        this.guildID = guildID;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
        this.imageURL = imageURL;
        this.message = message;
    }

    /**
     * @return The {@link String guildID} of the {@link GuildWelcome}.
     */
    @NotNull
    public String getGuildID() {
        return guildID;
    }

    /**
     * @return The {@link String description} of the {@link GuildWelcome}.
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * @return The {@link String thumbnailURL} of the {@link GuildWelcome}.
     */
    @Nullable
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    /**
     * @return The {@link String imageURL} of the {@link GuildWelcome}.
     */
    @Nullable
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @return The {@link String message} of the {@link GuildWelcome}.
     */
    @Nullable
    public String getMessage() {
        return message;
    }
}
