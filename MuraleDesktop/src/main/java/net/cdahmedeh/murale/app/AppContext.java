package net.cdahmedeh.murale.app;

import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.flow.WallpaperFlow;
import net.cdahmedeh.murale.ui.frame.MainFrame;
import sun.applet.Main;

/**
 * Created by cdahmedeh on 2/7/2017.
 */
public class AppContext {
    @Getter
    private static WallpaperFlow wallpaperFlow = new WallpaperFlow();

    @Getter
    private static EventBus eventBus = new EventBus();

    @Getter @Setter
    private static MainFrame mainFrame;

    public static void exit() {
        System.exit(0);
    }
}
