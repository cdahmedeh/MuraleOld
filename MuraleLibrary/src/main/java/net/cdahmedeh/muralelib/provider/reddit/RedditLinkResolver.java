package net.cdahmedeh.muralelib.provider.reddit;

import net.cdahmedeh.muralelib.provider.reddit.noembed.NoEmbedData;
import net.cdahmedeh.muralelib.provider.reddit.noembed.NoEmbedRequest;
import net.cdahmedeh.muralelib.util.net.NetTools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Resolver for reddit links. Attempts to get image from post.
 *
 * Created by cdahmedeh on 1/22/2017.
 */
public class RedditLinkResolver {
    private static String IMGUR_CDN_URL = "https://i.imgur.com/";
    private static String IMGUR_HASH_RE = "^https?:\\/\\/(?:i\\.|m\\.|edge\\.|www\\.)*imgur\\.com\\/(?:r\\/\\w+\\/)*(?!gallery)(?!removalrequest)(?!random)(?!memegen)((?:\\w{5}|\\w{7})(?:[&,](?:\\w{5}|\\w{7}))*)(?:#\\d+)?[a-z]?(\\.(?:jpe?g|gifv?|png))?(\\?.*)?$";

    private static String FLICKR_RE1 = "^https?:\\/\\/(?:\\w+\\.)?flickr\\.com\\/(?:.+)\\/(\\d{10,})(?:\\/|$).*";
    private static String FLICKR_RE2 = "^https?:\\/\\/(?:\\w+\\.)?flic\\.kr\\/p\\/(\\w+)(?:\\/|$).*";

    private static String REDDIT_RE = "(((((?:https?:)?\\/\\/)((?!about\\.)[\\w-]+?\\.)?(redd(?:it\\.com|\\.it)))(?!\\/(?:blog|about|code|advertising|jobs|rules|wiki|contact|buttons|gold|page|help|prefs|user|message|widget)\\b)((?:\\/r\\/[\\w-]+\\b(?<!\\/))|(?:\\/tb))?(\\/comments)??(\\/\\w{2,7}\\b(?<!\\/46ijrl)(?<!\\/wiki))((?:(?!\\))\\S)*)))";

    public static String resolve(String url) {
        if (isDirectLink(url)) {
            return url;
        }

        if (isImgur(url)) {
            return resolveImgur(url);
        }

        if (isFlickr(url)) {
            return resolveFlickr(url);
        }

        if (isReddit(url)) {
            return resolveReddit(url);
        }

        return null;
    }

    private static boolean isDirectLink(String url) {
        String contentType = NetTools.getContentType(url);
        return contentType.startsWith("image/");
    }

    private static boolean isImgur(String url) {
        return url.matches(IMGUR_HASH_RE);
    }

    private static String resolveImgur(String url) {
        Pattern pattern = Pattern.compile(IMGUR_HASH_RE);
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        String hash = matcher.group(1);
        String extension = ".jpg";

        return IMGUR_CDN_URL + "/" + hash + extension;
    }

    private static boolean isFlickr(String url) {
        return url.matches(FLICKR_RE1) || url.matches(FLICKR_RE2);
    }

    private static String resolveFlickr(String url) {
        NoEmbedRequest noEmbedRequest = new NoEmbedRequest();
        noEmbedRequest.setOriginalUrl(url);

        NoEmbedData noEmbedData = noEmbedRequest.getJson(NoEmbedData.class);
        return noEmbedData.getMedia_url();
    }

    private static boolean isReddit(String url) {
        return url.matches(REDDIT_RE);
    }

    private static String resolveReddit(String url) {
        String imageLink = null;

        try {
            Document document = Jsoup.connect(url).userAgent(NetTools.USER_AGENT).get();

            Elements elements = document.select("img.preview");
            for (Element element: elements) {
                imageLink = element.attr("src");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageLink;
    }
}
