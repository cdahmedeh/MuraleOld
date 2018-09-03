package net.cdahmedeh.muralelib.provider.reddit.api.json;

import lombok.Getter;
import net.cdahmedeh.muralelib.provider.reddit.api.json.RedditData;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditResponse {
    @Getter
    private String kind;

    @Getter
    private RedditData data;
}
