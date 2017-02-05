package net.cdahmedeh.murale.run;

import static net.cdahmedeh.murale.provider.reddit.RedditLinkResolver.resolve;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class Run001RedditLinksResolve {
    public static void main(String[] args) {
        System.out.println(resolve("https://i.redditmedia.com/JLj1MtePYRp09hEUcWX4MIlnZuzIeZfnKSFp7JUuODg.jpg?w=512&s=324742a6ba91b604094f777a7804111b"));
        System.out.println(resolve("https://www.reddit.com/r/EarthPorn/comments/5qlii7/banff_alberta_canada_oc_1200x800/"));
        System.out.println(resolve("http://imgur.com/ABgcWbx"));
        System.out.println(resolve("https://i.imgur.com//ABgcWbx.jpg"));
        System.out.println(resolve("https://www.flickr.com/photos/137417475@N04/32423680201/in/datetaken-public/"));
        System.out.println(resolve("https://www.reddit.com/r/EarthPorn/comments/5qstbj/saguaro_lake_arizona_5184x3456_oc/"));
    }
}
