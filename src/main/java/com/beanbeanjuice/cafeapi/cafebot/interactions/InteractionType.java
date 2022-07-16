package com.beanbeanjuice.cafeapi.cafebot.interactions;

import com.beanbeanjuice.cafeapi.cafebot.interactions.users.Interaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An enum used for {@link Interaction Interaction} types.
 *
 * @author beanbeanjuice
 * @since 1.3.1
 */
public enum InteractionType {

    HUG ("hug_amount", true, "hug"),
    PUNCH ("punch_amount", true, "punch"),
    KISS ("kiss_amount", true, "kiss"),
    BITE ("bite_amount", true, "bite"),
    BLUSH ("blush_amount", true, "blush"),
    CUDDLE ("cuddle_amount", true, "cuddle"),
    NOM ("nom_amount", true, "nom"),
    POKE ("poke_amount", true, "poke"),
    SLAP ("slap_amount", true, "slap"),
    STAB ("stab_amount", false, null),
    HMPH ("hmph_amount", false, null),
    POUT ("pout_amount", true, "pout"),
    THROW ("throw_amount", false, null),
    SMILE ("smile_amount", true, "smile"),
    STARE ("stare_amount", true, "stare"),
    TICKLE ("tickle_amount", true, "tickle"),
    RAGE ("rage_amount", false, null),
    YELL ("yell_amount", true, "scream"),
    HEADPAT ("headpat_amount", true, "pat"),
    CRY ("cry_amount", true, "cry"),
    DANCE ("dance_amount", true, "dance"),
    DAB ("dab_amount", false, null),
    BONK ("bonk_amount", false, null),
    SLEEP ("sleep_amount", true, "sleepy"),
    DIE ("die_amount", false, null),
    WELCOME ("welcome_amount", false, null),
    LICK ("lick_amount", true, "lick"),
    SHUSH ("shush_amount", false, null);

    private final String type;
    private final Boolean isKawaiiAPI;
    private final String kawaiiAPIString;

    /**
     * Creates a new {@link InteractionType} static object.
     * @param type The {@link String type} of {@link Interaction Interaction}.
     */
    InteractionType(@NotNull String type, @NotNull Boolean isKawaiiAPI, @Nullable String kawaiiAPIString) {
        this.type = type;
        this.isKawaiiAPI = isKawaiiAPI;
        this.kawaiiAPIString = kawaiiAPIString;
    }

    /**
     * @return Retrieves the {@link Interaction Interaction} type as a {@link String}.
     */
    @NotNull
    public String getType() {
        return type;
    }

    /**
     * @return True, if the image should be returned from the {@link com.beanbeanjuice.KawaiiAPI KawaiiAPI}.
     */
    @NotNull
    public Boolean isKawaiiAPI() {
        return isKawaiiAPI;
    }

    /**
     * @return The {@link String} for retrieving the {@link com.beanbeanjuice.KawaiiAPI KawaiiAPI} image.
     */
    @Nullable
    public String getKawaiiAPIString() {
        return kawaiiAPIString;
    }

}
