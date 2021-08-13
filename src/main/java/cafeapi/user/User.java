package cafeapi.user;

import org.jetbrains.annotations.NotNull;

public class User {

    private Integer id;
    private String username;
    private UserType userType;

    public User(@NotNull Integer id, @NotNull String username, @NotNull UserType userType) {
        this.id = id;
        this.username = username;
        this.userType = userType;
    }

    @NotNull
    public Integer getID() {
        return id;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @NotNull
    public UserType getUserType() {
        return userType;
    }
}
