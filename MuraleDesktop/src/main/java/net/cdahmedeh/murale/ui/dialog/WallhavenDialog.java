package net.cdahmedeh.murale.ui.dialog;

import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;
import com.ivkos.wallhaven4j.models.misc.enums.Category;
import com.ivkos.wallhaven4j.models.misc.enums.Purity;
import com.ivkos.wallhaven4j.models.misc.enums.Sorting;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.muralelib.provider.wallhaven.WallhavenProvider;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static net.cdahmedeh.muralelib.provider.reddit.RedditProvider.RedditMode.*;
import static net.cdahmedeh.muralelib.provider.reddit.RedditProvider.RedditTime.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class WallhavenDialog extends ProviderDialog<WallhavenProvider> {
    private WebTextField tagsField;
    private WebCheckBox peopleButton;
    private WebCheckBox generalButton;
    private WebCheckBox animeButton;
    private WebCheckBox sfwButton;
    private WebCheckBox sketchyButton;
    private WebCheckBox nsfwButton;
    private WebRadioButton relevanceButton;
    private WebRadioButton randomButton;
    private WebRadioButton dateAddedButton;
    private WebRadioButton viewsButton;
    private WebRadioButton favouritesButton;
    private WebRadioButton descButton;
    private WebRadioButton ascButton;
    private WebSpinner countField;


    public WallhavenDialog(WallhavenProvider provider) {
        super(provider);
    }

    @Override
    public WebPanel getFormPanel() {
        WebPanel formPanel = new WebPanel();
        formPanel.setLayout(new MigLayout("nogrid"));


        WebLabel tagsLabel = new WebLabel("Tags");
        tagsLabel.setBoldFont(true);
        formPanel.add(tagsLabel, "width 60, height 20");
        tagsField = new WebTextField();
        tagsField.setPreferredWidth(400);
        formPanel.add(tagsField, "wrap");


        WebLabel categoryLabel = new WebLabel("Category");
        categoryLabel.setBoldFont(true);
        formPanel.add(categoryLabel, "width 60, height 20");

        generalButton = new WebCheckBox("General");
        peopleButton = new WebCheckBox("People");
        animeButton = new WebCheckBox("Anime");

        formPanel.add(generalButton);
        formPanel.add(peopleButton);
        formPanel.add(animeButton, "wrap");


        WebLabel purityLabel = new WebLabel("Purity");
        purityLabel.setBoldFont(true);
        formPanel.add(purityLabel, "width 60, height 20");

        sfwButton = new WebCheckBox("Safe for Work");
        sketchyButton = new WebCheckBox("Sketchy");
        nsfwButton = new WebCheckBox("Not Safe for Work");

        formPanel.add(sfwButton);
        formPanel.add(sketchyButton);
        formPanel.add(nsfwButton, "wrap");
        

        WebLabel sortingLabel = new WebLabel("Sorting");
        sortingLabel.setBoldFont(true);
        formPanel.add(sortingLabel, "width 60, height 20");

        relevanceButton = new WebRadioButton("Relevance");
        randomButton = new WebRadioButton("Random");
        dateAddedButton = new WebRadioButton("Date Added");
        viewsButton = new WebRadioButton("Views");
        favouritesButton = new WebRadioButton("Favourites");

        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(relevanceButton);
        timeGroup.add(randomButton);
        timeGroup.add(dateAddedButton);
        timeGroup.add(viewsButton);
        timeGroup.add(favouritesButton);

        formPanel.add(relevanceButton, randomButton, dateAddedButton, viewsButton);
        formPanel.add(favouritesButton, "wrap");


        WebLabel orderLabel = new WebLabel("Order");
        orderLabel.setBoldFont(true);
        formPanel.add(orderLabel, "width 60, height 20");

        descButton = new WebRadioButton("Descending");
        ascButton = new WebRadioButton("Ascending");

        ButtonGroup orderGroup = new ButtonGroup();
        orderGroup.add(descButton);
        orderGroup.add(ascButton);

        formPanel.add(descButton);
        formPanel.add(ascButton, "wrap");


        WebLabel countLabel;
        countLabel = new WebLabel("Pages");
        countLabel.setBoldFont(true);
        formPanel.add(countLabel, "width 60, height 20");
        countField = new WebSpinner();
        countField.setValue(3);
        formPanel.add(countField, "width 100, wrap");


        return formPanel;
    }

    @Override
    public void loadProvider(WallhavenProvider provider) {
        tagsField.setText(provider.getTags());

        List<Category> categories = provider.getCategory();

        if (categories.contains(Category.GENERAL)) {
            generalButton.setSelected(true);
        }
        if (categories.contains(Category.ANIME)) {
            animeButton.setSelected(true);
        }
        if (categories.contains(Category.PEOPLE)) {
            peopleButton.setSelected(true);
        }

        List<Purity> purities = provider.getPurity();

        if (categories.contains(Purity.SFW)) {
            sfwButton.setSelected(true);
        }
        if (categories.contains(Purity.SKETCHY)) {
            sketchyButton.setSelected(true);
        }
        if (categories.contains(Purity.NSFW)) {
            nsfwButton.setSelected(true);
        }

        switch(provider.getSorting()) {
            case DATE_ADDED:
                dateAddedButton.setSelected(true);
                break;
            case FAVORITES:
                favouritesButton.setSelected(true);
                break;
            case RANDOM:
                randomButton.setSelected(true);
                break;
            case RELEVANCE:
                relevanceButton.setSelected(true);
                break;
            case VIEWS:
                viewsButton.setSelected(true);
                break;
        }

        switch(provider.getOrder()) {
            case ASC:
                ascButton.setSelected(true);
                break;
            case DESC:
                descButton.setSelected(true);
                break;
        }

        countField.setValue(provider.getPages());
    }

    @Override
    public void readProvider() {
        provider.setTags(tagsField.getText());

        List<Category> categories = new ArrayList<>();
        if (generalButton.isSelected()) {
            categories.add(Category.GENERAL);
        }
        if (peopleButton.isSelected()) {
            categories.add(Category.PEOPLE);
        }
        if (animeButton.isSelected()) {
            categories.add(Category.ANIME);
        }
        provider.setCategory(categories);

        List<Purity> purities = new ArrayList<>();
        if (sfwButton.isSelected()) {
            purities.add(Purity.SFW);
        }
        if (sketchyButton.isSelected()) {
            purities.add(Purity.SKETCHY);
        }
        if (nsfwButton.isSelected()) {
            purities.add(Purity.NSFW);
        }
        provider.setPurity(purities);

        if (relevanceButton.isSelected()) {
            provider.setSorting(Sorting.RELEVANCE);
        } else if (randomButton.isSelected()) {
            provider.setSorting(Sorting.RANDOM);
        } else if (dateAddedButton.isSelected()) {
            provider.setSorting(Sorting.DATE_ADDED);
        } else if (viewsButton.isSelected()) {
            provider.setSorting(Sorting.VIEWS);
        } else if (favouritesButton.isSelected()) {
            provider.setSorting(Sorting.FAVORITES);
        }

        provider.setPages(Integer.valueOf(countField.getValue().toString()));
    }

    @Override
    public WallhavenProvider loadNewProvider() {
        return new WallhavenProvider();
    }
}
