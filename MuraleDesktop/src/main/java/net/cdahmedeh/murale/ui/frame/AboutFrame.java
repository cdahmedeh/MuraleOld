package net.cdahmedeh.murale.ui.frame;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.ui.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/9/2017.
 */
public class AboutFrame extends WebFrame {
    public AboutFrame() {
        setTitle("About Murale");
        setIconImage(Icons.getIcon("about").getImage());

        setSize(320, 240);
        setLocationRelativeTo(null);

        WebPanel frame = new WebPanel();
        add(frame);

        BoxLayout manager = new BoxLayout(frame, BoxLayout.Y_AXIS);

        frame.setLayout(manager);

        WebLabel appName = new WebLabel("Murale");
        appName.setAlignmentX(CENTER_ALIGNMENT);
        appName.setFontSize(30);
        WebLabel version = new WebLabel("Version 0.1 Inception");
        version.setAlignmentX(CENTER_ALIGNMENT);
        version.setFontSize(20);
        JLabel imageIcon = new JLabel(Icons.getIcon("murale"));
        imageIcon.setAlignmentX(CENTER_ALIGNMENT);
        JLabel copyright = new JLabel("© cdahmedeh 2016");
        copyright.setAlignmentX(CENTER_ALIGNMENT);

        frame.add(appName);
        frame.add(version);
        frame.add(Box.createVerticalGlue());
        frame.add(imageIcon);
        frame.add(Box.createVerticalGlue());
        frame.add(copyright);

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);

        frame.setMargin(5);
        frame.setUndecorated(false);
    }
}
