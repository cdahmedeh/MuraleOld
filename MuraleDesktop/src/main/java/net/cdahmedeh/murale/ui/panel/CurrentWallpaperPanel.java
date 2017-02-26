package net.cdahmedeh.murale.ui.panel;

import com.alee.extended.image.WebDecoratedImage;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.progress.WebProgressOverlay;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.google.common.eventbus.Subscribe;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.event.TimePassedEvent;
import net.cdahmedeh.murale.event.WallpaperRequestEvent;
import net.cdahmedeh.murale.event.WallpaperRetrievedEvent;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.muralelib.domain.Wallpaper;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.miginfocom.swing.MigLayout;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by cdahmedeh on 2/6/2017.
 */
public class CurrentWallpaperPanel extends WebPanel {
    Wallpaper wallpaper = null;
    private Provider provider = null;

    private final WebDecoratedImage image;

    private WebLabel providerLabel;
    private WebLabel titleLabel;
    private WebLabel authorLabel;
    private WebLinkLabel originLabel;
    private final WebProgressOverlay overlay;
    private final WebProgressBar statusBar;

    public CurrentWallpaperPanel() {
        AppContext.getEventBus().register(this);

        WebPanel borderPanel = new WebPanel();
        add(borderPanel);

        setMargin(0, 3, 2, 2);

        borderPanel.setPreferredHeight(145);

        borderPanel.setBorder(BorderFactory.createTitledBorder("Current Wallpaper"));

        overlay = new WebProgressOverlay();
        overlay.setProgressColor(Color.WHITE);
        overlay.setConsumeEvents(false);

        image = new WebDecoratedImage();

        BufferedImage blankImage = new BufferedImage(142, 80, TYPE_INT_RGB);
        Graphics g = blankImage.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, blankImage.getWidth(), blankImage.getHeight());
        image.setIcon(new ImageIcon(blankImage));

        image.setDrawGlassLayer(true);
        image.setRound(0);
        image.setShadeWidth(5);

        overlay.setComponent(image);

        borderPanel.add(new InfoPanel(), BorderLayout.CENTER);
        borderPanel.add(overlay, BorderLayout.WEST);

        statusBar = new WebProgressBar();
        statusBar.setStringPainted(true);
        statusBar.setString("");
        borderPanel.add(statusBar, BorderLayout.SOUTH);
    }

    @Subscribe
    private void newWallpaperRequested(WallpaperRequestEvent event) {
        overlay.setShowLoad(true);

    }

    @Subscribe
    private void wallpaperUpdated(WallpaperRetrievedEvent event) {
        overlay.setShowLoad(false);

        wallpaper = AppContext.getWallpaperFlow().getCurrentWallpaper();
        provider = AppContext.getWallpaperFlow().getCurrentProvider();
        updateImage();
        updateText();
    }

    @Subscribe
    private void timePassed(TimePassedEvent event) {
        statusBar.setMaximum(ConfigurationService.loadUserConfiguration().getWaitTime()*60);
        int timeLeft = AppContext.getWallpaperFlow().getTimeLeft();
        if (timeLeft >= 0) {
            statusBar.setValue(timeLeft);
            DecimalFormat df = new DecimalFormat("00");
            statusBar.setString(timeLeft/60 + ":" + df.format(timeLeft%60) + " left until next wallpaper change");
        } else {
            statusBar.setValue(0);
        }
    }

    private void updateText() {
        providerLabel.setText(provider.getName() + " [" + provider.getDescription().replaceAll("<.*?>", "") + "]");
        titleLabel.setText(wallpaper.getTitle());
        authorLabel.setText(wallpaper.getAuthor());
        originLabel.setLink(wallpaper.getOrigin());
        originLabel.setOnPressAction(true);
    }

    private void updateImage()  {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedImage srcImage = null;
                try {
                    srcImage = ImageIO.read(new File(wallpaper.getLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedImage scaledImage = Scalr.resize(srcImage, Scalr.Mode.FIT_TO_HEIGHT,80);

                image.setIcon(new ImageIcon(scaledImage));

                image.setPreferredSize(new Dimension(scaledImage.getWidth() , scaledImage.getHeight() ));

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        image.revalidate();
                        revalidate();
                        getParent().revalidate();
                        getParent().getParent().revalidate();
                    }
                });
            }
        });

        thread.start();
    }

    public class InfoPanel extends WebPanel {
        public InfoPanel() {
            setLayout(new MigLayout());

            WebLabel providerHeader = new WebLabel("Provider");
            providerHeader.setBoldFont(true);
            add(providerHeader);

            providerLabel = new WebLabel("None");
            add(providerLabel, "wrap");

            WebLabel titleHeader = new WebLabel("Title");
            titleHeader.setBoldFont(true);
            add(titleHeader);

            titleLabel = new WebLabel("None");
            add(titleLabel, "wrap");

            WebLabel authorHeader = new WebLabel("Author");
            authorHeader.setBoldFont(true);
            add(authorHeader);

            authorLabel = new WebLabel("None");
            add(authorLabel, "wrap");

            WebLabel originHeader = new WebLabel("Origin");
            originHeader.setBoldFont(true);
            add(originHeader);

            originLabel = new WebLinkLabel("None");
            add(originLabel);
        }
    }


}
