package io.github.beanbeanjuice.cafeapi.cafebot.minigames.winstreaks;

import org.jetbrains.annotations.NotNull;

/**
 * An enum used for Minigame {@link WinStreak} types.
 *
 * @author beanbeanjuice
 */
public enum MinigameType {

    TIC_TAC_TOE ("tic_tac_toe"),
    CONNECT_FOUR ("connect_four");

    private final String type;

    /**
     * Creates a {@link MinigameType} enum.
     * @param type The {@link String type} of enum.
     */
    MinigameType(@NotNull String type) {
        this.type = type;
    }

    /**
     * @return The {@link String type} of {@link MinigameType}.
     */
    @NotNull
    public String getType() {
        return type;
    }

}
