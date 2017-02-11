package net.cdahmedeh.murale.ui.tray;

import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebPopupMenu;
import com.alee.managers.style.skin.web.PopupStyle;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by cdahmedeh on 2/8/2017.
 */
public class TrayIconSetup {
    public static void setup() {
        PopupMenu trayMenu = new PopupMenu();
        TrayIcon trayIcon = new TrayIcon(Icons.getIcon("murale-16").getImage(), "Murale");
        SystemTray systemTray = SystemTray.getSystemTray();

        MenuItem nextWallpaperItem = new MenuItem("Next Wallpaper");
        trayMenu.add(nextWallpaperItem);

        nextWallpaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getWallpaperFlow().nextWallpaper();
            }
        });

        trayMenu.addSeparator();

        MenuItem hideWindow = new MenuItem();
        trayMenu.add(hideWindow);

        if (AppContext.getMainFrame().isVisible()) {
            hideWindow.setLabel("Hide Murale");
        } else {
            hideWindow.setLabel("Show Murale");
        }

        hideWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getMainFrame().setVisible(
                        !AppContext.getMainFrame().isVisible()
                );

                if (AppContext.getMainFrame().isVisible()) {
                    hideWindow.setLabel("Hide Murale");
                } else {
                    hideWindow.setLabel("Show Murale");
                }
            }
        });

        MenuItem closeMurale = new MenuItem("Exit Murale");
        trayMenu.add(closeMurale);

        closeMurale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        trayIcon.setPopupMenu(trayMenu);

        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getMainFrame().setVisible(
                        !AppContext.getMainFrame().isVisible()
                );
            }
        });

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
