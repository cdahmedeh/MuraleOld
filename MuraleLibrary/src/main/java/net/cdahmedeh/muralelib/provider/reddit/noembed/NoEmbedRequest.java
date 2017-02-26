package net.cdahmedeh.muralelib.provider.reddit.noembed;

import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.muralelib.util.net.GetRequest;

/**
 * Created by cdahmedeh on 1/28/2017.
 */
public class NoEmbedRequest extends GetRequest {
    @Getter
    @Setter
    private String originalUrl;

    @Override
    public String getUrl() {
        return "https://noembed.com/embed?url=" + originalUrl;
    }
}
