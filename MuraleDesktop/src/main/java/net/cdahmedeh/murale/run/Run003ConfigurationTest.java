package net.cdahmedeh.murale.run;

import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.service.ConfigurationService;

import java.util.List;

/**
 * Created by cdahmedeh on 2/1/2017.
 */
public class Run003ConfigurationTest {
    public static void main(String args[]) {
        RedditProvider redditProvider = new RedditProvider();

        ConfigurationService.saveProvider(redditProvider);
        ConfigurationService.saveProvider(redditProvider);

        redditProvider.setSubreddit("earthporn");

        ConfigurationService.saveProvider(redditProvider);

        List<Provider> providers = ConfigurationService.loadProviders();

        System.out.println(providers);
    }
}
