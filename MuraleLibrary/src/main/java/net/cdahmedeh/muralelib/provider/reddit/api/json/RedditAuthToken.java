package net.cdahmedeh.muralelib.provider.reddit.api.json;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditAuthToken {
    @Getter
    @SerializedName("access_token")
    private String accessToken;

    @Getter
    @SerializedName("token_type")
    private String tokenType;

    @Getter
    @SerializedName("expires_in")
    private long expiresIn;
}
