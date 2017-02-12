package net.cdahmedeh.muralelib.provider.reddit.api.json;

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
