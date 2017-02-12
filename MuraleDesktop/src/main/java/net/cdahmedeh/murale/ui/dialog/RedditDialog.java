package net.cdahmedeh.murale.ui.dialog;

import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static net.cdahmedeh.muralelib.provider.reddit.RedditProvider.RedditMode.*;
import static net.cdahmedeh.muralelib.provider.reddit.RedditProvider.RedditTime.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class RedditDialog extends ProviderDialog<RedditProvider> {
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
    private WebCheckBox nsfwCheck;

    public RedditDialog(RedditProvider provider) {
        super(provider);
    }

    @Override
    public WebPanel getFormPanel() {
        WebPanel formPanel = new WebPanel();
        formPanel.setLayout(new MigLayout("nogrid"));


        WebLabel subredditLabel = new WebLabel("Subreddit");
        subredditLabel.setBoldFont(true);
        formPanel.add(subredditLabel, "width 60, height 20");
        subredditField = new WebTextField("all");
        subredditField.setPreferredWidth(200);
        formPanel.add(subredditField);

        formPanel.add(new WebButton("Visit Page", Icons.getIcon("visit-page")), "wrap");


        WebLabel modeLabel = new WebLabel("Mode");
        modeLabel.setBoldFont(true);
        formPanel.add(modeLabel, "width 60, height 20");

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

        formPanel.add(hotButton, newButton, risingButton, controversialButton, topButton);
        formPanel.add(randomButtom, "wrap");


        WebLabel timeLabel = new WebLabel("Time");
        timeLabel.setBoldFont(true);
        formPanel.add(timeLabel, "width 60, height 20");

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

        formPanel.add(hourButton, dayButton, weekButton, monthButton, yearButton);
        formPanel.add(allButton, "wrap");


        WebLabel countLabel;
        countLabel = new WebLabel("Count");
        countLabel.setBoldFont(true);
        formPanel.add(countLabel, "width 60, height 20");
        countField = new WebSpinner();
        countField.setValue(25);
        formPanel.add(countField, "width 100, wrap");


        WebLabel nsfwLabel;
        nsfwLabel = new WebLabel("Show NSFW Content");
        nsfwLabel.setBoldFont(true);
        formPanel.add(nsfwLabel, "width 60, height 20");
        nsfwCheck = new WebCheckBox();
        nsfwCheck.setSelected(false);
        formPanel.add(nsfwCheck, "wrap");

        return formPanel;
    }

    @Override
    public void loadProvider(RedditProvider provider) {
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
        nsfwCheck.setSelected(provider.isNsfw());
    }

    @Override
    public void readProvider() {
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
        provider.setNsfw(nsfwCheck.isSelected());
    }

    @Override
    public RedditProvider loadNewProvider() {
        return new RedditProvider();
    }
}
