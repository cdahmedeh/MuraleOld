package net.cdahmedeh.muralelib.provider.reddit.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.cdahmedeh.muralelib.provider.reddit.api.json.RedditAuthToken;
import net.cdahmedeh.muralelib.util.net.GetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
@RequiredArgsConstructor
public class RedditRequest extends GetRequest {
    @Getter private final String subreddit;
    @Getter private final String mode;
    @Getter private final String time;
    @Getter private final int count;


    @Override
    public String getUrl() {
        String t = "";
        if (mode.equals("top") || mode.equals("controversial")) {
            t = "&t=" + time;
        }

        if (mode.equals("random")) {
            return "https://oauth.reddit.com/r/" + subreddit + "/" + mode;
        } else {
            return "https://oauth.reddit.com/r/" + subreddit + "/" + mode + "?" + "count=" + count + t;
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        RedditAuthRequest redditAuthRequest = new RedditAuthRequest();
        RedditAuthToken token = redditAuthRequest.getJson(RedditAuthToken.class);

        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization", "bearer " + token.getAccessToken());

        return headers;
    }

}
