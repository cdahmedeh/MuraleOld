package net.cdahmedeh.murale.run;

import net.cdahmedeh.muralelib.domain.Wallpaper;
import net.cdahmedeh.murale.service.DesktopService;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class Run008SetWallpaperTest {
    public static void main(String[] args) {
        RedditProvider redditProvider = new RedditProvider();
        redditProvider.setSubreddit("cityporn");

        Wallpaper wallpaper = redditProvider.getRandomWallpaper();
        DesktopService.setWallpaper(wallpaper, null);
    }
}
