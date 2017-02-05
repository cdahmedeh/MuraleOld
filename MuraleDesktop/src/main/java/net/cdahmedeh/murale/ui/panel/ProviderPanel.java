package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.panel.WebPanelUI;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.domain.Provider;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.ui.component.HorizontalSeperator;

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

//        setStyleId(StyleId.panelTransparent);
        setOpaque(false);
        setBackground(Color.white);

        setLayout(new BorderLayout());

        add(new TitlePanel(), BorderLayout.WEST);

        add(new ButtonPanel(), BorderLayout.EAST);

//        HorizontalSeperator comp = new HorizontalSeperator();
//        add(comp, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public class TitlePanel extends WebPanel {
        public TitlePanel() {
            setLayout(new BorderLayout());

            JLabel title = new JLabel();
            title.setIcon(Icons.getIcon("reddit"));
            Font titleFont = title.getFont();
            Font newTitleFont = new Font(titleFont.getName(), titleFont.getStyle(), 16);
            title.setFont(newTitleFont);
            title.setText(provider.getTitle());
            add(title, BorderLayout.NORTH);

            JLabel description = new JLabel();
            description.setText(provider.getInfo());
            add(description, BorderLayout.SOUTH);

            setOpaque(false);
//            setBackground(Color.white);


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
                    if (provider instanceof RedditProvider) {
                        RedditPanel redditPanel = new RedditPanel();
                        redditPanel.loadProvider((RedditProvider)provider);
                    }
                }
            });

            WebButton deleteButton = new WebButton();
            deleteButton.setIcon(Icons.getIcon("delete"));
            deleteButton.setRolloverDecoratedOnly(true);
            deleteButton.setDrawFocus(true);
            add(deleteButton);
            deleteButton.setRound(2);
            setOpaque(false);
        }
    }
}
