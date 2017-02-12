package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.button.WebSplitButton;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.menu.*;
import com.alee.laf.panel.WebPanel;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.ui.dialog.RedditDialog;

import javax.swing.*;
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

            add(new MenuBarPanel(), BorderLayout.WEST);
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

            WebPopupMenu webMenu = new WebPopupMenu();
            addProvider.setPopupMenu(webMenu);

            WebMenuItem redditItem = new WebMenuItem("reddit", Icons.getIcon("reddit"));
            webMenu.add(redditItem);

            redditItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RedditDialog redditDialog = new RedditDialog(null);
                }
            });
        }
    }

    public class ProvidersPanel extends WebPanel {
        public ProvidersPanel() {
            setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
            add(new ProvidersListPanel(), BorderLayout.CENTER);
        }
    }
}
