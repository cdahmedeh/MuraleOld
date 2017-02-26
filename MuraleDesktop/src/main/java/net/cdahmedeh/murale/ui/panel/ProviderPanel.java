package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.ui.dialog.ProviderDialog;
import net.cdahmedeh.murale.ui.dialog.WallhavenDialog;
import net.cdahmedeh.muralelib.domain.Configuration;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.dialog.RedditDialog;
import net.cdahmedeh.muralelib.provider.wallhaven.WallhavenProvider;

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

        addSelectedCheckbox();

        add(new TitlePanel(), BorderLayout.CENTER);
        add(new ButtonPanel(), BorderLayout.EAST);
    }

    private void addSelectedCheckbox() {
        WebCheckBox selectedCheck = new WebCheckBox();
        selectedCheck.setMargin(0,0,0,10);
        add(selectedCheck, BorderLayout.WEST);

        selectedCheck.setSelected(provider.isEnabled());

        selectedCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provider.setEnabled(selectedCheck.isSelected());
                ConfigurationService.saveProvider(provider);
            }
        });
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
                    int result = WebOptionPane.showConfirmDialog(ProviderPanel.this, "Are you sure you want to remove this provider?", "Confirm Deletion...", WebOptionPane.YES_NO_OPTION);
                    if (result == WebOptionPane.OK_OPTION) {
                        ConfigurationService.deleteProvider(provider);
                    }
                }
            });
        }
    }

    private void loadSettings() {
        if (provider instanceof RedditProvider) {
            RedditDialog redditDialog = new RedditDialog((RedditProvider)provider);
        } else if (provider instanceof WallhavenProvider) {
            WallhavenDialog wallhavenDialog = new WallhavenDialog((WallhavenProvider)provider);
        }
    }
}
