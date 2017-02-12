package net.cdahmedeh.murale.ui.frame;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.rootpane.WebFrame;
import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.run.Run006MainPanel;
import net.cdahmedeh.murale.ui.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public class MainFrame extends WebFrame {
    public MainFrame() {
        setTitle("Murale");
        setIconImage(Icons.getIcon("murale").getImage());

        setSize(640, 540);
        setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void toggleVisible() {
        if (getState() == ICONIFIED) {
            setState(NORMAL);
        } else {
            setVisible(!isVisible());
        }
    }
}
