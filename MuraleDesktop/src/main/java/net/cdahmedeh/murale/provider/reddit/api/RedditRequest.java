package net.cdahmedeh.murale.provider.reddit.api;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.util.net.GetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditRequest extends GetRequest {

    @Getter @Setter private String method;
    @Getter @Setter private String subreddit;
    @Getter @Setter private int count;
    @Getter @Setter private String time;

    @Override
    public String getUrl() {
        String t = "";
        if (method.equals("top") || method.equals("controversial")) {
            t = "&t=" + time;
        }

        if (method.equals("random")) {
            return "https://oauth.reddit.com/r/" + subreddit + "/" + method;
        } else {
            return "https://oauth.reddit.com/r/" + subreddit + "/" + method + "?" + "count=" + count + t;
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        RedditAuthRequest redditAuthRequest = new RedditAuthRequest();
        redditAuthRequest.get();
        RedditAuthToken token = redditAuthRequest.getJson();

        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization", "bearer " + token.getAccessToken());

        return headers;
    }

    public RedditResponse getJson() {
        return new Gson().fromJson(responseContent, RedditResponse.class);
    }

}
