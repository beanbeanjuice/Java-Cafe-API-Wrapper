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

import java.util.TimeZone;

public class CafeAPI {

    private String apiKey;
    private String userAgent;
    private static RequestLocation requestLocation;

    public Users USER;

    public Words WORD;
    public Welcomes WELCOME;
    public VoiceChannelBinds VOICE_CHANNEL_BIND;
    public Raffles RAFFLE;
    public Polls POLL;
    public WinStreaks WIN_STREAK;
    public Interactions INTERACTION;
    public GuildTwitches TWITCH;
    public GuildInformations GUILD;
    public GeneratedCodes GENERATED_CODE;
    public Versions VERSION;
    public CountingInformations COUNTING_INFORMATION;
    public CafeUsers CAFE_USER;
    public Birthdays BIRTHDAY;
    public DonationUsers DONATION_USER;
    public InteractionPictures INTERACTION_PICTURE;

    /**
     * Creates a new {@link CafeAPI} object.
     * @param username The {@link String username}.
     * @param password The {@link String password}.
     * @param requestLocation The {@link RequestLocation requestLocation}.
     */
    public CafeAPI(@NotNull String username, @NotNull String password, @NotNull RequestLocation requestLocation) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        this.userAgent = username;
        CafeAPI.requestLocation = requestLocation;

        try {
            apiKey = getToken(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        USER = new Users(apiKey);

        // cafeBot
        WORD = new Words(apiKey);
        WELCOME = new Welcomes(apiKey);
        VOICE_CHANNEL_BIND = new VoiceChannelBinds(apiKey);
        RAFFLE = new Raffles(apiKey);
        POLL = new Polls(apiKey);
        WIN_STREAK = new WinStreaks(apiKey);
        INTERACTION = new Interactions(apiKey);
        TWITCH = new GuildTwitches(apiKey);
        GUILD = new GuildInformations(apiKey);
        GENERATED_CODE = new GeneratedCodes(apiKey);
        VERSION = new Versions(apiKey);
        COUNTING_INFORMATION = new CountingInformations(apiKey);
        CAFE_USER = new CafeUsers(apiKey);
        BIRTHDAY = new Birthdays(apiKey);
        DONATION_USER = new DonationUsers(apiKey);
        INTERACTION_PICTURE = new InteractionPictures(apiKey);
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

}
