package net.cdahmedeh.murale.flow;

import lombok.Getter;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.event.TimePassedEvent;
import net.cdahmedeh.murale.event.WallpaperRequestEvent;
import net.cdahmedeh.murale.event.WallpaperRetrievedEvent;
import net.cdahmedeh.murale.provider.Provider;
import net.cdahmedeh.murale.domain.Configuration;
import net.cdahmedeh.murale.domain.Wallpaper;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.service.DesktopService;
import net.cdahmedeh.murale.util.sys.TimeTools;
import net.cdahmedeh.murale.util.type.CollectionTools;

import java.awt.*;
import java.util.List;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class WallpaperFlow {

    @Getter private Wallpaper currentWallpaper;

    @Getter private Provider currentProvider;

    @Getter
    private int timeLeft = -1;

    public WallpaperFlow() {
        Thread timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    TimeTools.sleep(1000);

                    if (timeLeft >= 0) {
                        timeLeft--;
                    }

                    System.out.println(timeLeft);
                    AppContext.getEventBus().post(new TimePassedEvent());

                    if (timeLeft == 0) {
                        nextWallpaper();
                    }
                }
            }
        });

        timerThread.start();
    }

    public void loadConfiguration() {
        timeLeft = ConfigurationService.loadUserConfiguration().getWaitTime() * 60;
    }

    public void nextWallpaper() {
        AppContext.getEventBus().post(new WallpaperRequestEvent());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Provider> providers = ConfigurationService.loadProviders();
                currentProvider = CollectionTools.pickRandom(providers);
                currentWallpaper = currentProvider.getRandomWallpaper();

                Configuration configuration = ConfigurationService.loadUserConfiguration();
                timeLeft = configuration.getWaitTime() * 60;

                DesktopService.setWallpaper(currentWallpaper, configuration);

                AppContext.getEventBus().post(new WallpaperRetrievedEvent());
            }
        });
        thread.start();
    }
}
