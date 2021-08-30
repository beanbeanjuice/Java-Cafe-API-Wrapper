package cafeapi.cafebot.words;

import org.jetbrains.annotations.NotNull;

public class Word {

    private String word;
    private Integer uses;

    public Word(@NotNull String word, @NotNull Integer uses) {
        this.word = word;
        this.uses = uses;
    }

    public String getWord() {
        return word;
    }

    public Integer getUses() {
        return uses;
    }
}
