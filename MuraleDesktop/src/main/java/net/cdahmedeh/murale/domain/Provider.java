package net.cdahmedeh.murale.domain;

import lombok.Getter;
import net.cdahmedeh.murale.domain.Wallpaper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public abstract class Provider {
    @Getter
    private String uuid = UUID.randomUUID().toString();

    public Wallpaper getWallpaper() {
        return getRandomWallpaper();
    }

    public void loadConfiguration(Map<String, String> configuration) {
        uuid = configuration.getOrDefault("uuid", UUID.randomUUID().toString());
    }

    public Map<String, String> getConfiguration() {
        Map<String, String> config = new HashMap<>();
        config.put("uuid", uuid);
        config.put("class", getClass().getName());
        return config;
    };

    public abstract Wallpaper getRandomWallpaper();

    public abstract String getTitle();

    public abstract String getInfo();
}
