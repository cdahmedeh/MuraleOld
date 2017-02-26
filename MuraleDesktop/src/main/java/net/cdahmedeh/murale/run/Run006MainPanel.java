package net.cdahmedeh.murale.run;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.murale.ui.panel.MainPanel;
import net.cdahmedeh.murale.ui.panel.ProvidersListPanel;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class Run006MainPanel extends JFrame {
    public Run006MainPanel() {
        setBounds(100,100,800,600);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        WebLookAndFeel.install();

        new Run006MainPanel();
    }
}
