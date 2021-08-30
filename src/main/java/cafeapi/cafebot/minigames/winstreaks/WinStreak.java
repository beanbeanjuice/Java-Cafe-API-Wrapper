package cafeapi.cafebot.minigames.winstreaks;

import org.jetbrains.annotations.NotNull;

/**
 * A class used for a user's {@link WinStreak}.
 *
 * @author beanbeanjuice
 */
public class WinStreak {

    private final Integer ticTacToeWins;
    private final Integer connectFourWins;

    /**
     * Creates a new {@link WinStreak}.
     * @param ticTacToeWins The {@link Integer amount} of {@link MinigameType TIC_TAC_TOE} wins.
     * @param connectFourWins The {@link Integer amount} of {@link MinigameType CONNECT_FOUR} wins.
     */
    public WinStreak(@NotNull Integer ticTacToeWins, @NotNull Integer connectFourWins) {
        this.ticTacToeWins = ticTacToeWins;
        this.connectFourWins = connectFourWins;
    }

    /**
     * @return The {@link Integer amount} of {@link MinigameType TIC_TAC_TOE} wins.
     */
    @NotNull
    public Integer getTicTacToeWins() {
        return ticTacToeWins;
    }

    /**
     * @return The {@link Integer amount} of {@link MinigameType CONNECT_FOUR} wins.
     */
    @NotNull
    public Integer getConnectFourWins() {
        return connectFourWins;
    }
}
