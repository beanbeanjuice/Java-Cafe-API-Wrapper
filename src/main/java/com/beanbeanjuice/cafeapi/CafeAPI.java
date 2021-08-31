package com.beanbeanjuice.cafeapi;

import com.beanbeanjuice.cafeapi.cafebot.beancoins.users.DonationUsers;
import com.beanbeanjuice.cafeapi.cafebot.birthdays.Birthdays;
import com.beanbeanjuice.cafeapi.cafebot.cafe.CafeUsers;
import com.beanbeanjuice.cafeapi.cafebot.codes.GeneratedCodes;
import com.beanbeanjuice.cafeapi.cafebot.counting.CountingInformations;
import com.beanbeanjuice.cafeapi.cafebot.guilds.GuildInformations;
import com.beanbeanjuice.cafeapi.cafebot.interactions.Interactions;
import com.beanbeanjuice.cafeapi.cafebot.interactions.pictures.InteractionPictures;
import com.beanbeanjuice.cafeapi.cafebot.minigames.winstreaks.WinStreaks;
import com.beanbeanjuice.cafeapi.cafebot.polls.Polls;
import com.beanbeanjuice.cafeapi.cafebot.raffles.Raffles;
import com.beanbeanjuice.cafeapi.cafebot.twitches.GuildTwitches;
import com.beanbeanjuice.cafeapi.cafebot.version.Versions;
import com.beanbeanjuice.cafeapi.cafebot.voicebinds.VoiceChannelBinds;
import com.beanbeanjuice.cafeapi.cafebot.welcomes.Welcomes;
import com.beanbeanjuice.cafeapi.cafebot.words.Words;
import com.beanbeanjuice.cafeapi.requests.Request;
import com.beanbeanjuice.cafeapi.requests.RequestBuilder;
import com.beanbeanjuice.cafeapi.requests.RequestRoute;
import com.beanbeanjuice.cafeapi.requests.RequestType;
import com.beanbeanjuice.cafeapi.user.Users;
import org.jetbrains.annotations.NotNull;

public class CafeAPI {

    private String apiKey;
    private String userAgent;
    public static final String url = "http://localhost:4101/cafe/api/v1";

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

    public CafeAPI(@NotNull String username, @NotNull String password) {
        this.userAgent = username;

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

    // TODO: Reset API Key

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
