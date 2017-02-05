package net.cdahmedeh.murale.provider.reddit.api;

import com.google.gson.Gson;
import net.cdahmedeh.murale.util.net.PostRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.cdahmedeh.murale.util.net.NetTools.generateBasicAuthHeader;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditAuthRequest extends PostRequest {
    @Override
    public String getUrl() {
        return "https://www.reddit.com/api/v1/access_token";
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", generateBasicAuthHeader("wLQzgOCgA1ZbxQ", ""));
        return headers;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "https://oauth.reddit.com/grants/installed_client");
        params.put("device_id", UUID.randomUUID().toString());
        return params;
    }

    public RedditAuthToken getJson() {
        return new Gson().fromJson(responseContent, RedditAuthToken.class);
    }
}
