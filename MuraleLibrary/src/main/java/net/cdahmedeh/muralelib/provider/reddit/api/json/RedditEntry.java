package net.cdahmedeh.muralelib.provider.reddit.api.json;

import lombok.Getter;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditEntry {
    @Getter
    private String kind;

    @Getter
    private RedditPost data;
}
