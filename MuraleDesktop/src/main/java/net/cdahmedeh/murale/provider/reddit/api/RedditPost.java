package net.cdahmedeh.murale.provider.reddit.api;

import lombok.Getter;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditPost {
    @Getter
    private String domain;

    @Getter
    private String author;

    @Getter
    private int score;

    @Getter
    private String url;

    @Getter
    private String permalink;

    @Getter
    private String title;
}
