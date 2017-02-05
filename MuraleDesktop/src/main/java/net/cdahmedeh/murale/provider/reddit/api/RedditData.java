package net.cdahmedeh.murale.provider.reddit.api;

import lombok.Getter;

import java.util.Collection;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditData {
    @Getter
    private String modhash;

    @Getter
    private Collection<RedditEntry> children;

    @Getter
    private String after;

    @Getter
    private String before;
}
