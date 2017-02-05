package net.cdahmedeh.murale.ui.frame;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.rootpane.WebFrame;
import net.cdahmedeh.murale.run.Run006MainPanel;
import net.cdahmedeh.murale.ui.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public class MainFrame extends WebFrame {
    public MainFrame() {
        setBounds(100,100,640,480);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
