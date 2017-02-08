package net.cdahmedeh.murale.ui.panel;

import net.cdahmedeh.murale.provider.Provider;
import net.cdahmedeh.murale.service.ConfigurationService;
import net.cdahmedeh.murale.ui.custom.ColouredComponentPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class ProvidersListPanel extends JScrollPane {
    private final ColouredComponentPanel pane;

    public ProvidersListPanel() {
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

    private void loadProviders() {
        List<Provider> providers = ConfigurationService.loadProviders();

        for (Provider provider: providers) {
            ProviderPanel providerPanel = new ProviderPanel(provider);
            pane.addElement(providerPanel);
        }
    }

}
