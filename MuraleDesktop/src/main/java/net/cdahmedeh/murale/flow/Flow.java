package net.cdahmedeh.murale.flow;

import net.cdahmedeh.murale.domain.Provider;
import net.cdahmedeh.murale.domain.UserConfiguration;
import net.cdahmedeh.murale.domain.Wallpaper;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.util.type.CollectionTools;

import java.util.List;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class Flow {
    Thread thread = null;

    public void nextWallpaper() {
        List<Provider> providers = ConfigurationService.loadProviders();

        Provider provider = loadRandom(providers);

        Wallpaper wallpaper = provider.getWallpaper();

        UserConfiguration configuration = ConfigurationService.loadUserConfiguration();

        DesktopManager.setWallpaper(wallpaper, configuration);
    }

    public void pause() {
        UserConfiguration configuration = ConfigurationService.loadUserConfiguration();

        pause(configuration.getWaitTime());
    }

    public void pause(int minutes) {
        if (thread != null) {
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(minutes * 60 * 1000);
                } catch (InterruptedException e) {

                }
            }
        });

        thread.start();
    }

    public void interrupt() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private Provider loadRandom(List<Provider> providers) {
        return CollectionTools.pickRandom(providers);
    }
}
