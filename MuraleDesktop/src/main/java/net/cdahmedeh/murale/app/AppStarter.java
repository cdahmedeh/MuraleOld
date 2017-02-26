package net.cdahmedeh.murale.app;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.ui.frame.MainFrame;
import net.cdahmedeh.murale.ui.tray.AppTrayIcon;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public class AppStarter {
    public static void main(String[] args) {
        WebLookAndFeel.install();

        AppTrayIcon.show();
        AppContext.setMainFrame(new MainFrame());

        AppContext.getWallpaperFlow().loadConfiguration();
    }
}
