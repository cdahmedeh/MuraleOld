package net.cdahmedeh.murale.run;

import net.cdahmedeh.murale.domain.Wallpaper;
import net.cdahmedeh.murale.provider.reddit.RedditProvider;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class Run002RedditProviderTest {
    public static void main(String[] args) {
        RedditProvider redditProvider = new RedditProvider();
        redditProvider.setSubreddit("earthporn");

        Wallpaper wallpaper = redditProvider.getRandomWallpaper();
        System.out.println(wallpaper);
    }
}
