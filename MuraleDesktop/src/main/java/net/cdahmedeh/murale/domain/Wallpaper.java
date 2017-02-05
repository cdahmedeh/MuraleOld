package net.cdahmedeh.murale.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
@ToString
public class Wallpaper {
    @Getter @Setter private String title;
    @Getter @Setter private String author;
    @Getter @Setter private String page;
    @Getter @Setter private String url;
}
