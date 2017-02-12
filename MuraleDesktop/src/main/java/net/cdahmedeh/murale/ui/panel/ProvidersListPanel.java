package net.cdahmedeh.murale.ui.panel;

import com.google.common.eventbus.Subscribe;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.event.ProviderUpdatedEvent;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.custom.ColouredComponentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class ProvidersListPanel extends JScrollPane {
    private ColouredComponentPanel pane;

    public ProvidersListPanel() {
        AppContext.getEventBus().register(this);

        pane = new ColouredComponentPanel();
        pane.setReorderingAllowed(true);

        setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0,0,128f/256f), 1));
        setBackground(Color.white);
        setOpaque(true);

        setViewportView(pane);

        getViewport().setBackground(Color.white);

        pane.setBackground(Color.white);

        loadProviders();
    }

    @Subscribe
    public void updateProviders(ProviderUpdatedEvent event) {
        loadProviders();
    }

    private void loadProviders() {
        List<Provider> providers = ConfigurationService.loadProviders();

        clearPane();

        for (Provider provider: providers) {
            ProviderPanel providerPanel = new ProviderPanel(provider);
            pane.addElement(providerPanel);
        }
    }

    private void clearPane() {
        pane.removeAll();

        pane = new ColouredComponentPanel();
        pane.setReorderingAllowed(true);
        setViewportView(pane);
        pane.setBackground(Color.white);

        revalidate();
    }

}
