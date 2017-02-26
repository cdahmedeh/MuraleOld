package net.cdahmedeh.muralelib.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Information about a wallpaper.
 *
 * Created by cdahmedeh on 1/30/2017.
 */
@RequiredArgsConstructor
public class Wallpaper {
    @Getter private final String uuid = UUID.randomUUID().toString();
    @Getter private final String title;
    @Getter private final String author;
    @Getter private final String origin;
    @Getter private final String url;

    @Getter @Setter private String location;
}
