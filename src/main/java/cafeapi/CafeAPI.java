package cafeapi;

import cafeapi.cafebot.interactions.Interactions;
import cafeapi.cafebot.minigames.winstreaks.WinStreaks;
import cafeapi.cafebot.polls.Polls;
import cafeapi.cafebot.raffles.Raffles;
import cafeapi.cafebot.twitches.GuildTwitches;
import cafeapi.cafebot.voicebinds.VoiceChannelBinds;
import cafeapi.cafebot.welcomes.Welcomes;
import cafeapi.cafebot.words.Words;
import cafeapi.requests.Request;
import cafeapi.requests.RequestBuilder;
import cafeapi.requests.RequestRoute;
import cafeapi.requests.RequestType;
import cafeapi.user.Users;
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

}
