package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.dialog.RedditDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cdahmedeh on 2/1/2017.
 */
public class ProviderPanel extends WebPanel {
    @Getter @Setter
    private Provider provider;

    public ProviderPanel(Provider provider) {
        this.provider = provider;

        setLayout(new BorderLayout());

        setOpaque(false);
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new TitlePanel(), BorderLayout.WEST);
        add(new ButtonPanel(), BorderLayout.EAST);
    }

    public class TitlePanel extends WebPanel {
        public TitlePanel() {
            setLayout(new BorderLayout());

            WebLabel title = new WebLabel(provider.getName());
            title.setIcon(Icons.getIcon(provider.getIconName()));
            title.setFontSize(16);
            add(title, BorderLayout.NORTH);

            JLabel description = new JLabel();
            description.setText(provider.getDescription());
            add(description, BorderLayout.SOUTH);

            setOpaque(false);
        }
    }

    public class ButtonPanel extends GroupPanel {
        public ButtonPanel() {
            WebButton settingsButton = new WebButton();
            settingsButton.setIcon(Icons.getIcon("settings"));
            settingsButton.setRound(2);
            settingsButton.setRolloverDecoratedOnly(true);
            settingsButton.setDrawFocus(true);
            add(settingsButton);

            settingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadSettings();
                }
            });

            WebButton deleteButton = new WebButton();
            deleteButton.setIcon(Icons.getIcon("delete"));
            deleteButton.setRolloverDecoratedOnly(true);
            deleteButton.setDrawFocus(true);
            add(deleteButton);
            deleteButton.setRound(2);
            setOpaque(false);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ConfigurationService.deleteProvider(provider);
                }
            });
        }
    }

    private void loadSettings() {
        if (provider instanceof RedditProvider) {
            RedditDialog redditDialog = new RedditDialog((RedditProvider)provider);
        }
    }
}
