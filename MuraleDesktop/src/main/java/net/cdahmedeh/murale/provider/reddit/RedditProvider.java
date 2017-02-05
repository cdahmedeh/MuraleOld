package net.cdahmedeh.murale.provider.reddit;

import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.domain.Provider;
import net.cdahmedeh.murale.domain.Wallpaper;
import net.cdahmedeh.murale.provider.reddit.api.RedditEntry;
import net.cdahmedeh.murale.provider.reddit.api.RedditPost;
import net.cdahmedeh.murale.provider.reddit.api.RedditRequest;
import net.cdahmedeh.murale.provider.reddit.api.RedditResponse;
import net.cdahmedeh.murale.util.type.CollectionTools;

import java.util.Collection;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class RedditProvider extends Provider {
    public enum RedditMode {
        Hot, New, Rising, Controversial, Top, Random
    }

    public enum RedditTime {
        Hour, Day, Week, Month, Year, All
    }

    @Getter @Setter
    private RedditMode redditMode = RedditMode.Hot;

    @Getter @Setter
    private RedditTime redditTime = RedditTime.Month;

    @Getter @Setter
    private int redditCount = 25;

    @Getter @Setter
    private String subreddit = "all";

    @Override
    public String getTitle() {
        return "Reddit";
    }

    @Override
    public String getInfo() {
        return "<html> subreddit: <b>" + subreddit + "</b> - " + redditCount + " " + redditMode.toString().toLowerCase() + " posts from past " + redditTime.toString().toLowerCase() + "</html>";
    }

    @Override
    public void loadConfiguration(Map<String, String> configuration) {
        super.loadConfiguration(configuration);
        redditMode = RedditMode.valueOf(configuration.getOrDefault("mode", "Hot"));
        redditTime = RedditTime.valueOf(configuration.getOrDefault("time", "Month"));
        redditCount = Integer.valueOf(configuration.getOrDefault("count", "25"));
        subreddit = configuration.getOrDefault("subreddit", "all");
    }

    @Override
    public Map<String, String> getConfiguration() {
        Map<String, String> configuration = super.getConfiguration();

        configuration.put("mode", redditMode.name());
        configuration.put("time", redditTime.name());
        configuration.put("count", String.valueOf(redditCount));
        configuration.put("subreddit", subreddit);

        return configuration;
    }

    @Override
    public Wallpaper getRandomWallpaper() {
        Wallpaper wallpaper = new Wallpaper();
        String resolved = null;

        while (resolved == null) {
            RedditRequest request = new RedditRequest();
            request.setMethod(redditMode.name().toLowerCase());
            request.setCount(redditCount);
            request.setSubreddit(subreddit);
            request.setTime(redditTime.name().toLowerCase());

            request.get();
            RedditResponse redditResponse = request.getJson();

            Collection<RedditEntry> redditEntries = redditResponse.getData().getChildren();

            RedditPost redditPost = CollectionTools.pickRandom(redditEntries).getData();

            wallpaper.setTitle(redditPost.getTitle());
            wallpaper.setAuthor(redditPost.getAuthor());
            wallpaper.setPage(redditPost.getPermalink());

            resolved = RedditLinkResolver.resolve(redditPost.getUrl());
            wallpaper.setUrl(resolved);
        }

        return wallpaper;
    }
}
