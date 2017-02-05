package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
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
            JButton settingsButton = new JButton();
            settingsButton.setIcon(Icons.getIcon("settings"));
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

            JButton deleteButton = new JButton();
            deleteButton.setIcon(Icons.getIcon("delete"));
            add(deleteButton);

            setOpaque(false);
        }
    }
}
