package net.cdahmedeh.murale.ui.starter;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.ui.frame.MainFrame;
import net.cdahmedeh.murale.ui.tray.TrayIconSetup;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public class UIStarter {
    public static void main(String[] args) {
        WebLookAndFeel.install();

        AppContext.setMainFrame(new MainFrame());
        TrayIconSetup.setup();

        AppContext.getWallpaperFlow().loadConfiguration();
    }
}
