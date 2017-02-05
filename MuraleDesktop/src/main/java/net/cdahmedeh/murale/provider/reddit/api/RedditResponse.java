package net.cdahmedeh.murale.provider.reddit.api;

import lombok.Getter;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditResponse {
    @Getter
    private String kind;

    @Getter
    private RedditData data;
}
