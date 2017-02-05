package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.menu.MenuBarStyle;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.hotkey.Hotkey;
import net.cdahmedeh.murale.icon.Icons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class MainPanel extends WebPanel {
    public class HeaderPanel extends WebPanel {
        public HeaderPanel() {
            setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

//            JLabel providers = new JLabel("Wallpaper Providers");
//            Font providersFont = providers.getFont();
//            Font newFont = new Font(providersFont.getName(), providersFont.getStyle(), 18);
//            providers.setFont(newFont);
//            add(providers, BorderLayout.WEST);

            WebMenuBar menuBar = new WebMenuBar();
            menuBar.setMenuBarStyle(MenuBarStyle.standalone);
            add(menuBar, BorderLayout.WEST);

            setupMenuBar(menuBar);

            add(new HeaderButtonPanel(), BorderLayout.EAST);
        }
    }

    public class HeaderButtonPanel extends GroupPanel {
        public HeaderButtonPanel() {
            JButton addGenre = new JButton("Add Preset", Icons.getIcon("add-genre"));
            add(addGenre);

            JButton addProvider = new JButton("Add Provider", Icons.getIcon("add-provider"));
            add(addProvider);
        }
    }

    public class ProvidersPanel extends WebPanel {
        public ProvidersPanel() {
            setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
            add(new ProvidersListPanel(), BorderLayout.CENTER);
        }
    }

    public MainPanel() {
        add(new HeaderPanel(), BorderLayout.NORTH);
        add(new ProvidersPanel(), BorderLayout.CENTER);
    }

    public void setupMenuBar (WebMenuBar menuBar) {
        menuBar.add(new WebMenu("File", Icons.getIcon("file")));
        menuBar.add(new WebMenu("Wallpaper", Icons.getIcon("wallpaper")));
        menuBar.add(new WebMenu("Options", Icons.getIcon("config")));
        menuBar.add(new WebMenu("Help", Icons.getIcon("help")));
    }
}
