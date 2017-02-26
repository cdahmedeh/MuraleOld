package net.cdahmedeh.murale.run;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.murale.ui.panel.ProvidersListPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class Run005ProvidersList extends JFrame {
    public Run005ProvidersList() {
        setBounds(100,100,800,600);

        ProvidersListPanel providersListPanel = new ProvidersListPanel();
        add(providersListPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        WebLookAndFeel.install();

        new Run005ProvidersList();
    }
}
