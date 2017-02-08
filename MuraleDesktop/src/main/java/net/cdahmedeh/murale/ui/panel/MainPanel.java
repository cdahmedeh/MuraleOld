package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.button.WebSplitButton;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.menu.MenuBarStyle;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.hotkey.Hotkey;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.ui.starter.UIStarter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class MainPanel extends WebPanel {
    public MainPanel() {
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new ProvidersPanel(), BorderLayout.CENTER);
        add(new CurrentWallpaperPanel(), BorderLayout.SOUTH);
    }

    public class HeaderPanel extends WebPanel {
        public HeaderPanel() {
            setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            WebMenuBar menuBar = new WebMenuBar();
            menuBar.setMenuBarStyle(MenuBarStyle.standalone);
            menuBar.setUndecorated(true);
            add(menuBar, BorderLayout.WEST);

            setupMenuBar(menuBar);

            add(new HeaderButtonPanel(), BorderLayout.EAST);
        }
    }

    public class HeaderButtonPanel extends GroupPanel {
        public HeaderButtonPanel() {
            WebSplitButton addGenre = new WebSplitButton("Add Preset", Icons.getIcon("add-genre"));
            addGenre.setAlwaysShowMenu(true);
            add(addGenre);

            WebSplitButton addProvider = new WebSplitButton("Add Provider", Icons.getIcon("add-provider"));
            addProvider.setAlwaysShowMenu(true);
            add(addProvider);
        }
    }

    public class ProvidersPanel extends WebPanel {
        public ProvidersPanel() {
            setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
            add(new ProvidersListPanel(), BorderLayout.CENTER);
        }
    }

    public void setupMenuBar (WebMenuBar menuBar) {
        menuBar.add(new WebMenu("File", Icons.getIcon("file")));
        WebMenu wallpaperMenu = new WebMenu("Wallpaper", Icons.getIcon("wallpaper"));
        menuBar.add(wallpaperMenu);
        menuBar.add(new WebMenu("Options", Icons.getIcon("config")));
        menuBar.add(new WebMenu("Help", Icons.getIcon("help")));

        WebMenuItem nextWallpaperItem = new WebMenuItem("Next Wallpaper", Icons.getIcon("next"));
        wallpaperMenu.add(nextWallpaperItem);

        nextWallpaperItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContext.getWallpaperFlow().nextWallpaper();
            }
        });
    }
}
