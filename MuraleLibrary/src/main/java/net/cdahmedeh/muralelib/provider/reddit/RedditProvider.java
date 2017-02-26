package net.cdahmedeh.muralelib.provider.reddit;

import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.muralelib.domain.Wallpaper;
import net.cdahmedeh.muralelib.provider.reddit.api.json.RedditEntry;
import net.cdahmedeh.muralelib.provider.reddit.api.json.RedditPost;
import net.cdahmedeh.muralelib.provider.reddit.api.request.RedditRequest;
import net.cdahmedeh.muralelib.provider.reddit.api.request.RedditResponse;
import net.cdahmedeh.muralelib.util.type.CollectionTools;

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
    private static boolean defaultNsfw = false;

    @Getter @Setter
    private String subreddit = defaultSubreddit;

    @Getter @Setter
    private RedditMode redditMode = defaultMode;

    @Getter @Setter
    private RedditTime redditTime = defaultTime;

    @Getter @Setter
    private int redditCount = defaultCount;

    @Getter @Setter
    private boolean nsfw = defaultNsfw;

     @Override
    public String getName() {
        return "reddit";
    }

    @Override
    public String getDescription() {
        return "<html>subreddit: <b>" + subreddit + "</b> - " + redditCount + " "
                + redditMode.toString().toLowerCase() + " posts from past "
                + redditTime.toString().toLowerCase() + "</html>";
    }

    @Override
    public String getIconName() {
        return "reddit";
    }

    @Override
    public void loadConfiguration(Map<String, String> configuration) {
        super.loadConfiguration(configuration);
        subreddit = configuration.getOrDefault("subreddit", defaultSubreddit);
        redditMode = RedditMode.valueOf(configuration.getOrDefault("mode", defaultMode.toString()));
        redditTime = RedditTime.valueOf(configuration.getOrDefault("time", defaultTime.toString()));
        redditCount = Integer.valueOf(configuration.getOrDefault("count", String.valueOf(defaultCount)));
        nsfw = Boolean.valueOf(configuration.getOrDefault("nsfw", String.valueOf(defaultNsfw)));
    }

    @Override
    public Map<String, String> getConfiguration() {
        Map<String, String> configuration = super.getConfiguration();

        configuration.put("subreddit", subreddit);
        configuration.put("mode", redditMode.name());
        configuration.put("time", redditTime.name());
        configuration.put("count", String.valueOf(redditCount));
        configuration.put("nsfw", Boolean.toString(nsfw));

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

            if (isNsfw() == false && redditPost.isNsfw()) {
                continue;
            }

            resolvedUrl = RedditLinkResolver.resolve(redditPost.getUrl());

            wallpaper = new Wallpaper(
                    redditPost.getTitle(),
                    redditPost.getAuthor(),
                    "http://reddit.com" + redditPost.getPermalink(),
                    resolvedUrl
            );
        }

        return wallpaper;
    }
}
