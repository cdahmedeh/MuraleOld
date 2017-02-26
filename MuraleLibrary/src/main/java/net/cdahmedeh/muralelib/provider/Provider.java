package net.cdahmedeh.muralelib.provider;

import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.muralelib.domain.Wallpaper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Base class for a provider
 *
 * Created by cdahmedeh on 1/30/2017.
 */
public abstract class Provider {
    private static String createUuid() {
        return UUID.randomUUID().toString();
    }

    @Getter
    private String uuid = createUuid();

    @Getter
    private String clazz = getClass().getName();

    @Getter @Setter
    private boolean enabled = true;

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getIconName();

    public void loadConfiguration(Map<String, String> configuration) {
        uuid = configuration.getOrDefault("uuid", createUuid());
        clazz = configuration.getOrDefault("class", getClass().getName());
        enabled = Boolean.valueOf(configuration.getOrDefault("enabled", "true"));
    }

    public Map<String, String> getConfiguration() {
        Map<String, String> config = new HashMap<>();
        config.put("uuid", uuid);
        config.put("class", getClass().getName());
        config.put("enabled", String.valueOf(enabled));
        return config;
    }

    public abstract Wallpaper getRandomWallpaper();
}
