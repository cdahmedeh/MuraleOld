package net.cdahmedeh.muralelib.provider.reddit.noembed;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public class NoEmbedData {
    @Getter
    @SerializedName("media_url")
    private String mediaUrl;
}
