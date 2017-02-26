package net.cdahmedeh.muralelib.provider.wallhaven;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.ivkos.wallhaven4j.Wallhaven;
import com.ivkos.wallhaven4j.models.misc.enums.Category;
import com.ivkos.wallhaven4j.models.misc.enums.Order;
import com.ivkos.wallhaven4j.models.misc.enums.Purity;
import com.ivkos.wallhaven4j.models.misc.enums.Sorting;
import com.ivkos.wallhaven4j.util.searchquery.SearchQuery;
import com.ivkos.wallhaven4j.util.searchquery.SearchQueryBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import net.cdahmedeh.muralelib.domain.Wallpaper;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.muralelib.util.type.CollectionTools;

import java.util.*;

/**
 * Created by cdahmedeh on 2/19/2017.
 */
public class WallhavenProvider extends Provider {
    private static final String defaultTags = "";
    private static final List<Category> defaultCategory = new ArrayList<>();
    private static final List<Purity> defaultPurity = Lists.newArrayList(Purity.SFW);
    private static final Sorting defaultSorting = Sorting.VIEWS;
    private static final Order defaultOrder = Order.DESC;
    private static final int defaultPages = 3;

    @Getter @Setter
    private String tags = defaultTags;

    @Getter @Setter
    private List<Category> category = defaultCategory;

    @Getter @Setter
    private List<Purity> purity = defaultPurity;

    @Getter @Setter
    private Sorting sorting = defaultSorting;

    @Getter @Setter
    private Order order = defaultOrder;

    @Getter @Setter
    private int pages = defaultPages;

    @Override
    public String getName() {
        return "wallhaven.cc";
    }

    @Override
    public String getDescription() {
        String desc = "<html>";

        desc += "tags: ";
        desc += "<b>[";
        desc += tags;
        desc += "]</b>";

        desc += "</html>";
        return desc;
    }

    @Override
    public String getIconName() {
        return "wallhaven";
    }

    @Override
    public void loadConfiguration(Map<String, String> configuration) {
        super.loadConfiguration(configuration);
        tags = configuration.getOrDefault("tags", defaultTags);

        List<Category> categories = new ArrayList<>();
        String categoryString = configuration.getOrDefault("category", null);
        for (String categoryValue: categoryString.split(",")) {
            if (categoryValue.isEmpty()) continue;
            categories.add(Category.valueOf(categoryValue));
        }
        if (categoryString != null) {
            category = categories;
        } else {
            category = new ArrayList<>(defaultCategory);
        }

        List<Purity> purities = new ArrayList<>();
        String purityString = configuration.getOrDefault("purity", null);
        for (String purityValue: purityString.split(",")) {
            if (purityValue.isEmpty()) continue;
            purities.add(Purity.valueOf(purityValue));
        }

        if (purityString != null) {
            purity = purities;
        } else {
            purity = new ArrayList<>(defaultPurity);
        }

        sorting = Sorting.valueOf(configuration.getOrDefault("sorting", defaultSorting.toString()));
        order = Order.valueOf(configuration.getOrDefault("order", defaultOrder.toString()));
        pages = Integer.valueOf(configuration.getOrDefault("pages", String.valueOf(defaultPages)));
    }

    @Override
    public Map<String, String> getConfiguration() {
        Map<String, String> configuration = super.getConfiguration();

        configuration.put("tags", tags);
        configuration.put("category", Joiner.on(",").join(category));
        configuration.put("purity", Joiner.on(",").join(purity));
        configuration.put("sorting", sorting.name());
        configuration.put("order", order.name());
        configuration.put("pages", String.valueOf(pages));

        return configuration;
    }

    @Override
    public Wallpaper getRandomWallpaper() {
        Wallhaven wh = new Wallhaven();

        SearchQuery query = new SearchQueryBuilder()
                .keywords(tags.split(" "))
                .categories(category)
                .purity(purity)
                .sorting(sorting)
                .order(order)
                .pages(pages)
                .build();

        val results = wh.search(query);

        val result = CollectionTools.pickRandom(results);

        Wallpaper wallpaper = new Wallpaper(
                result.getTags().toString(),
                result.getUser().getUsername(),
                result.getUrl(),
                result.getImageUrl()
        );

        return wallpaper;
    }
}
