package net.cdahmedeh.murale.ui.panel;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.provider.Provider;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.template.ProviderDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.cdahmedeh.murale.provider.reddit.RedditProvider.RedditMode.*;
import static net.cdahmedeh.murale.provider.reddit.RedditProvider.RedditTime.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class RedditPanel extends WebFrame implements ProviderDialog<RedditProvider> {

    @Getter
    @Setter
    private Provider provider;

    private WebTextField subredditField;
    private WebRadioButton hotButton;
    private WebRadioButton newButton;
    private WebRadioButton risingButton;
    private WebRadioButton controversialButton;
    private WebRadioButton topButton;
    private WebRadioButton randomButtom;
    private WebRadioButton hourButton;
    private WebRadioButton dayButton;
    private WebRadioButton weekButton;
    private WebRadioButton monthButton;
    private WebRadioButton yearButton;
    private WebRadioButton allButton;
    private WebSpinner countField;

    public RedditPanel() {
        setBounds(new Rectangle(640, 480));

        setLayout(new BorderLayout());

        add(new FormPanel(), BorderLayout.CENTER);
        add(new HeaderButtonPanel(), BorderLayout. SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public class FormPanel extends WebPanel {
        public FormPanel() {
            setLayout(new MigLayout("nogrid"));

            setUndecorated(false);
            setMargin(5);
            setRound(5);

            WebLabel subredditLabel = new WebLabel("Subreddit");
            subredditLabel.setBoldFont(true);
            add(subredditLabel, "width 60, height 20");
            subredditField = new WebTextField("all");
            subredditField.setPreferredWidth(200);
            add(subredditField);

            add(new WebButton("Visit Page", Icons.getIcon("visit-page")), "wrap");

            WebLabel modeLabel = new WebLabel("Mode");
            modeLabel.setBoldFont(true);
            add(modeLabel, "width 60, height 20");

            hotButton = new WebRadioButton("Hot");
            newButton = new WebRadioButton("New");
            risingButton = new WebRadioButton("Rising");
            controversialButton = new WebRadioButton("Controversial");
            topButton = new WebRadioButton("Top");
            randomButtom = new WebRadioButton("Random");

            ButtonGroup modeGroup = new ButtonGroup();
            modeGroup.add(hotButton);
            modeGroup.add(newButton);
            modeGroup.add(risingButton);
            modeGroup.add(controversialButton);
            modeGroup.add(topButton);
            modeGroup.add(randomButtom);

            add(hotButton, newButton, risingButton, controversialButton, topButton);
            add(randomButtom, "wrap");

            //

            WebLabel timeLabel = new WebLabel("Time");
            timeLabel.setBoldFont(true);
            add(timeLabel, "width 60, height 20");

            hourButton = new WebRadioButton("Last Hour");
            dayButton = new WebRadioButton("Last Day");
            weekButton = new WebRadioButton("Last Week");
            monthButton = new WebRadioButton("Last Month");
            yearButton = new WebRadioButton("Last Year");
            allButton = new WebRadioButton("All Time");

            ButtonGroup timeGroup = new ButtonGroup();
            timeGroup.add(hourButton);
            timeGroup.add(dayButton);
            timeGroup.add(weekButton);
            timeGroup.add(monthButton);
            timeGroup.add(yearButton);
            timeGroup.add(allButton);

            add(hourButton, dayButton, weekButton, monthButton, yearButton);
            add(allButton, "wrap");

            //--
            WebLabel countLabel;
            countLabel = new WebLabel("Count");
            countLabel.setBoldFont(true);
            add(countLabel, "width 60, height 20");
            countField = new WebSpinner();
            countField.setValue(25);
            add(countField, "width 100, wrap");
        }
    }

    @Override
    public void loadProvider(RedditProvider provider) {
        setProvider(provider);

        subredditField.setText(provider.getSubreddit());

        switch(provider.getRedditMode()){
            case Hot:
                hotButton.setSelected(true);
                break;
            case New:
                newButton.setSelected(true);
                break;
            case Rising:
                risingButton.setSelected(true);
                break;
            case Controversial:
                controversialButton.setSelected(true);
                break;
            case Top:
                topButton.setSelected(true);
                break;
            case Random:
                randomButtom.setSelected(true);
                break;
        }

        switch(provider.getRedditTime()) {
            case Hour:
                hourButton.setSelected(true);
                break;
            case Day:
                dayButton.setSelected(true);
                break;
            case Week:
                weekButton.setSelected(true);
                break;
            case Month:
                monthButton.setSelected(true);
                break;
            case Year:
                yearButton.setSelected(true);
                break;
            case All:
                allButton.setSelected(true);
                break;
        }

        countField.setValue(provider.getRedditCount());
    }

    @Override
    public RedditProvider readProvider(RedditProvider provider) {
        provider.setSubreddit(subredditField.getText());

        if (hotButton.isSelected()) {
            provider.setRedditMode(Hot);
        } else if (newButton.isSelected()) {
            provider.setRedditMode(New);
        } else if (risingButton.isSelected()) {
            provider.setRedditMode(Rising);
        } else if (controversialButton.isSelected()) {
            provider.setRedditMode(Controversial);
        } else if (topButton.isSelected()) {
            provider.setRedditMode(Top);
        } else if (randomButtom.isSelected()) {
            provider.setRedditMode(Random);
        }

        if (hourButton.isSelected()) {
            provider.setRedditTime(Hour);
        } else if (dayButton.isSelected()) {
            provider.setRedditTime(Day);
        } else if (weekButton.isSelected()) {
            provider.setRedditTime(Week);
        } else if (monthButton.isSelected()) {
            provider.setRedditTime(Month);
        } else if (yearButton.isSelected()) {
            provider.setRedditTime(Year);
        } else if (allButton.isSelected()) {
            provider.setRedditTime(All);
        }

        provider.setRedditCount(Integer.valueOf(countField.getValue().toString()));

        return provider;
    }

    public class HeaderButtonPanel extends WebPanel {
        public HeaderButtonPanel() {
            setLayout(new BoxLayout(HeaderButtonPanel.this, BoxLayout.X_AXIS));

            add(Box.createHorizontalGlue());

            WebButton cancelButton = new WebButton("Cancel", Icons.getIcon("cancel"));
            cancelButton.setMargin(5);
            add(cancelButton);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dispose();
                }
            });

            WebButton okButton = new WebButton("Ok", Icons.getIcon("save"));
            okButton.setMargin(5);
            add(okButton);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    provider = readProvider((RedditProvider)provider);
                    ConfigurationService.saveProvider(provider);

                    setVisible(false);
                    dispose();
                }
            });
        }
    }
}
