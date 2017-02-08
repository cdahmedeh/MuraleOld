package net.cdahmedeh.murale.app;

import com.google.common.eventbus.EventBus;
import lombok.Getter;
import net.cdahmedeh.murale.flow.WallpaperFlow;

/**
 * Created by cdahmedeh on 2/7/2017.
 */
public class AppContext {
    @Getter
    private static WallpaperFlow wallpaperFlow = new WallpaperFlow();

    @Getter
    private static EventBus eventBus = new EventBus();
}
