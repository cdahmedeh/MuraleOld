package net.cdahmedeh.murale.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class HorizontalSeperator extends JComponent {
    public HorizontalSeperator() {
        setMinimumSize(new Dimension((int)getMinimumSize().getWidth(), 10));
        setPreferredSize(new Dimension((int)getMinimumSize().getWidth(), 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(50,5, getWidth() - 1 - 50,5);
    }
}
