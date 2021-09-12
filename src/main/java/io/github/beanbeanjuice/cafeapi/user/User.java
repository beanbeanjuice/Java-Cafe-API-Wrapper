package io.github.beanbeanjuice.cafeapi.user;

import org.jetbrains.annotations.NotNull;

/**
 * A {@link User} class used for contextualising users in the API database.
 *
 * @author beanbeanjuice
 */
public class User {

    private final Integer id;
    private final String username;
    private final UserType userType; // TODO: Make something for updating the UserType

    /**
     * Creates a new {@link User}.
     * @param id The {@link Integer ID} of the {@link User}.
     * @param username The {@link String username} of the {@link User}.
     * @param userType The {@link UserType user type} of the {@link User}.
     */
    public User(@NotNull Integer id, @NotNull String username, @NotNull UserType userType) {
        this.id = id;
        this.username = username;
        this.userType = userType;
    }

    /**
     * @return The {@link Integer ID} of the {@link User}.
     */
    @NotNull
    public Integer getID() {
        return id;
    }

    /**
     * @return The {@link String username} of the {@link User}.
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    /**
     * @return The {@link UserType} of the {@link User}.
     */
    @NotNull
    public UserType getUserType() {
        return userType;
    }

}
