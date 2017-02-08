package net.cdahmedeh.murale.provider.reddit;

import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.provider.Provider;
import net.cdahmedeh.murale.domain.Wallpaper;
import net.cdahmedeh.murale.provider.reddit.api.*;
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

    private static String defaultSubreddit = "all";
    private static RedditMode defaultMode = RedditMode.Hot;
    private static RedditTime defaultTime = RedditTime.Month;
    private static int defaultCount = 25;

    @Getter @Setter
    private String subreddit = defaultSubreddit;

    @Getter @Setter
    private RedditMode redditMode = defaultMode;

    @Getter @Setter
    private RedditTime redditTime = defaultTime;

    @Getter @Setter
    private int redditCount = defaultCount;

     @Override
    public String getName() {
        return "Reddit";
    }

    @Override
    public String getDescription() {
        return "<html> subreddit: <b>" + subreddit + "</b> - " + redditCount + " "
                + redditMode.toString().toLowerCase() + " posts from past "
                + redditTime.toString().toLowerCase() + "</html>";
    }

    @Override
    public void loadConfiguration(Map<String, String> configuration) {
        super.loadConfiguration(configuration);
        subreddit = configuration.getOrDefault("subreddit", defaultSubreddit);
        redditMode = RedditMode.valueOf(configuration.getOrDefault("mode", defaultMode.toString()));
        redditTime = RedditTime.valueOf(configuration.getOrDefault("time", defaultTime.toString()));
        redditCount = Integer.valueOf(configuration.getOrDefault("count", String.valueOf(defaultCount)));
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
        Wallpaper wallpaper = null;
        String resolvedUrl = null;

        while (resolvedUrl == null) {
            RedditRequest request = new RedditRequest(
                    subreddit,
                    redditMode.name().toLowerCase(),
                    redditTime.name().toLowerCase(),
                    redditCount
                    );

            RedditResponse redditResponse = request.getJson(RedditResponse.class);

            Collection<RedditEntry> redditEntries = redditResponse.getData().getChildren();
            RedditPost redditPost = CollectionTools.pickRandom(redditEntries).getData();

            resolvedUrl = RedditLinkResolver.resolve(redditPost.getUrl());

            wallpaper = new Wallpaper(
                    redditPost.getTitle(),
                    redditPost.getAuthor(),
                    redditPost.getPermalink(),
                    resolvedUrl
            );
        }

        return wallpaper;
    }
}
