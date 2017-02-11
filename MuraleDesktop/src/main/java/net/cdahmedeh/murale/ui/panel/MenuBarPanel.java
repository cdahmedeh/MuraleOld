package net.cdahmedeh.murale.ui.panel;

import com.alee.laf.menu.MenuBarStyle;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.ui.frame.AboutFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cdahmedeh on 2/11/2017.
 */
public class MenuBarPanel extends WebPanel {
    public MenuBarPanel() {
        WebMenuBar menuBar = new WebMenuBar();
        menuBar.setMenuBarStyle(MenuBarStyle.standalone);
        menuBar.setUndecorated(true);
        add(menuBar, BorderLayout.CENTER);

        setupMenuBar(menuBar);
    }

    public void setupMenuBar (WebMenuBar menuBar) {
        WebMenu mainMenu = new WebMenu("File", Icons.getIcon("file"));
        menuBar.add(mainMenu);

        WebMenu wallpaperMenu = new WebMenu("Wallpaper", Icons.getIcon("wallpaper"));
        menuBar.add(wallpaperMenu);

        WebMenu configMenu = new WebMenu("Options", Icons.getIcon("config"));
        menuBar.add(configMenu);

        WebMenu timeMenu = new WebMenu("Wallpaper Change Interval", Icons.getIcon("interval"));
        configMenu.add(timeMenu);

        WebMenu helpMenu = new WebMenu("Help", Icons.getIcon("help"));
        menuBar.add(helpMenu);

        WebMenuItem hideWindow = new WebMenuItem("Hide Murale", Icons.getIcon("hide"));
        mainMenu.add(hideWindow);

        hideWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getMainFrame().setVisible(false);
            }
        });

        WebMenuItem closeMurale = new WebMenuItem("Exit Murale", Icons.getIcon("exit"));
        mainMenu.add(closeMurale);

        closeMurale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        WebMenuItem nextWallpaperItem = new WebMenuItem("Next Wallpaper", Icons.getIcon("next"));
        wallpaperMenu.add(nextWallpaperItem);

        nextWallpaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getWallpaperFlow().nextWallpaper();
            }
        });

        WebMenuItem aboutItem = new WebMenuItem("About", Icons.getIcon("about"));
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutFrame();
            }
        });
        helpMenu.add(aboutItem);
    }
}
