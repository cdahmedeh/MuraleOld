package net.cdahmedeh.murale.ui.panel;

import com.alee.laf.menu.*;
import com.alee.laf.panel.WebPanel;
import com.alee.utils.swing.AncestorAdapter;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.dialog.AboutDialog;
import net.cdahmedeh.muralelib.domain.Configuration;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
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

        createTimeMenu(timeMenu, 1, "1 minute");
        createTimeMenu(timeMenu, 5, "5 minutes");
        createTimeMenu(timeMenu, 10, "10 minutes");
        createTimeMenu(timeMenu, 15, "15 minutes");
        createTimeMenu(timeMenu, 20, "20 minutes");
        createTimeMenu(timeMenu, 30, "30 minutes");
        createTimeMenu(timeMenu, 60, "1 hour");
        createTimeMenu(timeMenu, 120, "2 hours");
        createTimeMenu(timeMenu, 240, "3 hours");

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
                new AboutDialog();
            }
        });
        helpMenu.add(aboutItem);
    }

    ButtonGroup timeGroup = new ButtonGroup();

    private void createTimeMenu(WebMenu timeMenu, int minutes, String label) {
        WebRadioButtonMenuItem item = new WebRadioButtonMenuItem(label);
        timeMenu.add(item);

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration configuration = ConfigurationService.loadUserConfiguration();
                configuration.setWaitTime(minutes);
                AppContext.getWallpaperFlow().setTimer(minutes*60);
                ConfigurationService.saveConfiguration(configuration);

                item.setSelected(true);
            }
        });

        item.addAncestorListener(new AncestorAdapter() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                Configuration configuration = ConfigurationService.loadUserConfiguration();

                if (configuration.getWaitTime() == minutes) {
                    item.setSelected(true);
                }
            }
        });

        timeGroup.add(item);
    }
}
