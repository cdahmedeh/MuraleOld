package net.cdahmedeh.murale.provider.reddit.noembed;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.util.net.GetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public class NoEmbedRequest extends GetRequest {
    @Getter @Setter private String originalUrl;

    @Override
    public String getUrl() {
        return "https://noembed.com/embed?url=" + originalUrl;
    }
}
