package cafeapi.cafebot.interactions;

import org.jetbrains.annotations.NotNull;

/**
 * An enum used for {@link cafeapi.cafebot.interactions.users.Interaction Interaction} types.
 *
 * @author beanbeanjuice
 */
public enum InteractionType {

    HUG ("hug_amount"),
    PUNCH ("punch_amount"),
    KISS ("kiss_amount"),
    BITE ("bite_amount"),
    BLUSH ("blush_amount"),
    CUDDLE ("cuddle_amount"),
    NOM ("nom_amount"),
    POKE ("poke_amount"),
    SLAP ("slap_amount"),
    STAB ("stab_amount"),
    HMPH ("hmph_amount"),
    POUT ("pout_amount"),
    THROW ("throw_amount"),
    SMILE ("smile_amount"),
    STARE ("stare_amount"),
    TICKLE ("tickle_amount"),
    RAGE ("rage_amount"),
    YELL ("yell_amount"),
    HEADPAT ("headpat_amount"),
    CRY ("cry_amount"),
    DANCE ("dance_amount"),
    DAB ("dab_amount"),
    BONK ("bonk_amount"),
    SLEEP ("sleep_amount"),
    DIE ("die_amount"),
    WELCOME ("welcome_amount"),
    LICK ("lick_amount"),
    SHUSH ("shush_amount");

    private final String type;

    /**
     * Creates a new {@link InteractionType} static object.
     * @param type The {@link String type} of {@link cafeapi.cafebot.interactions.users.Interaction Interaction}.
     */
    InteractionType(@NotNull String type) {
        this.type = type;
    }

    /**
     * @return Retrieves the {@link cafeapi.cafebot.interactions.users.Interaction Interaction} type as a {@link String}.
     */
    @NotNull
    public String getType() {
        return type;
    }
}
