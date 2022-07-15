package com.beanbeanjuice.cafeapi.requests;

import org.jetbrains.annotations.NotNull;

public enum RequestLocation {

    RELEASE ("http://beanbeanjuice.com:5100"),
    BETA ("http://beanbeanjuice.com:5101"),
    LOCAL ("http://localhost:5101");

    private final String url;

    /**
     * Creates a new static {@link RequestLocation}.
     * @param url The {@link String url} for the {@link RequestLocation}.
     */
    RequestLocation(@NotNull String url) {
        this.url = url;
    }

    /**
     * @return The URL for the specified {@link RequestLocation}.
     */
    @NotNull
    public String getURL() {
        return url;
    }

}
