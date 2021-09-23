package io.github.beanbeanjuice.cafeapi;

import io.github.beanbeanjuice.cafeapi.cafebot.beancoins.users.DonationUsers;
import io.github.beanbeanjuice.cafeapi.cafebot.birthdays.Birthdays;
import io.github.beanbeanjuice.cafeapi.cafebot.cafe.CafeUsers;
import io.github.beanbeanjuice.cafeapi.cafebot.codes.GeneratedCodes;
import io.github.beanbeanjuice.cafeapi.cafebot.counting.CountingInformations;
import io.github.beanbeanjuice.cafeapi.cafebot.guilds.GuildInformations;
import io.github.beanbeanjuice.cafeapi.cafebot.interactions.Interactions;
import io.github.beanbeanjuice.cafeapi.cafebot.interactions.pictures.InteractionPictures;
import io.github.beanbeanjuice.cafeapi.cafebot.minigames.winstreaks.WinStreaks;
import io.github.beanbeanjuice.cafeapi.cafebot.polls.Polls;
import io.github.beanbeanjuice.cafeapi.cafebot.raffles.Raffles;
import io.github.beanbeanjuice.cafeapi.cafebot.twitches.GuildTwitches;
import io.github.beanbeanjuice.cafeapi.cafebot.version.Versions;
import io.github.beanbeanjuice.cafeapi.cafebot.voicebinds.VoiceChannelBinds;
import io.github.beanbeanjuice.cafeapi.cafebot.welcomes.Welcomes;
import io.github.beanbeanjuice.cafeapi.cafebot.words.Words;
import io.github.beanbeanjuice.cafeapi.requests.*;
import io.github.beanbeanjuice.cafeapi.user.Users;
import org.jetbrains.annotations.NotNull;

public class CafeAPI {

    private String apiKey;
    private String userAgent;
    private static RequestLocation requestLocation;

    private Users users;

    private Words words;
    private Welcomes welcomes;
    private VoiceChannelBinds voiceChannelBinds;
    private Raffles raffles;
    private Polls polls;
    private WinStreaks winstreaks;
    private Interactions interactions;
    private GuildTwitches guildTwitches;
    private GuildInformations guildInformations;
    private GeneratedCodes generatedCodes;
    private Versions versions;
    private CountingInformations countingInformations;
    private CafeUsers cafeUsers;
    private Birthdays birthdays;
    private DonationUsers donationUsers;
    private InteractionPictures interactionPictures;

    /**
     * Creates a new {@link CafeAPI} object.
     * @param username The {@link String username}.
     * @param password The {@link String password}.
     * @param requestLocation The {@link RequestLocation requestLocation}.
     */
    public CafeAPI(@NotNull String username, @NotNull String password, @NotNull RequestLocation requestLocation) {
        this.userAgent = username;
        CafeAPI.requestLocation = requestLocation;

        try {
            apiKey = getToken(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        users = new Users(apiKey);

        // cafeBot
        words = new Words(apiKey);
        welcomes = new Welcomes(apiKey);
        voiceChannelBinds = new VoiceChannelBinds(apiKey);
        raffles = new Raffles(apiKey);
        polls = new Polls(apiKey);
        winstreaks = new WinStreaks(apiKey);
        interactions = new Interactions(apiKey);
        guildTwitches = new GuildTwitches(apiKey);
        guildInformations = new GuildInformations(apiKey);
        generatedCodes = new GeneratedCodes(apiKey);
        versions = new Versions(apiKey);
        countingInformations = new CountingInformations(apiKey);
        cafeUsers = new CafeUsers(apiKey);
        birthdays = new Birthdays(apiKey);
        donationUsers = new DonationUsers(apiKey);
        interactionPictures = new InteractionPictures(apiKey);
    }

    @NotNull
    public static RequestLocation getRequestLocation() {
        return requestLocation;
    }

    private String getToken(@NotNull String username, @NotNull String password) {
        Request request = new RequestBuilder(RequestRoute.CAFE, RequestType.POST)
                .setRoute("/user/login")
                .addParameter("username", username)
                .addParameter("password", password)
                .build();

        return request.getData().get("api_key").textValue();
    }

    public Users users() {
        return users;
    }

    public Words words() {
        return words;
    }

    public Welcomes welcomes() {
        return welcomes;
    }

    public VoiceChannelBinds voiceChannelBinds() {
        return voiceChannelBinds;
    }

    public Raffles raffles() {
        return raffles;
    }

    public Polls polls() {
        return polls;
    }

    public WinStreaks winStreaks() {
        return winstreaks;
    }

    public Interactions interactions() {
        return interactions;
    }

    public GuildTwitches guildTwitches() {
        return guildTwitches;
    }

    public GuildInformations guildInformations() {
        return guildInformations;
    }

    public GeneratedCodes generatedCodes() {
        return generatedCodes;
    }

    public Versions versions() {
        return versions;
    }

    public CountingInformations countingInformations() {
        return countingInformations;
    }

    public CafeUsers cafeUsers() {
        return cafeUsers;
    }

    public Birthdays birthdays() {
        return birthdays;
    }

    public DonationUsers donationUsers() {
        return donationUsers;
    }
    
    public InteractionPictures interactionPictures() {
        return interactionPictures;
    }
}
