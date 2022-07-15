package com.beanbeanjuice.cafeapi.cafebot.words;

import org.jetbrains.annotations.NotNull;

public class Word {

    private final String word;
    private final Integer uses;

    /**
     * Creates a new {@link Word} object.
     * @param word The {@link String word}.
     * @param uses The amount of {@link Integer uses} the {@link Word} has.
     */
    public Word(@NotNull String word, @NotNull Integer uses) {
        this.word = word;
        this.uses = uses;
    }

    /**
     * @return The {@link String word}.
     */
    @NotNull
    public String getWord() {
        return word;
    }

    /**
     * @return The amount of {@link Integer uses} the {@link Word} has.
     */
    @NotNull
    public Integer getUses() {
        return uses;
    }
}
