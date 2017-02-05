package net.cdahmedeh.murale.run;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.murale.ui.panel.RedditPanel;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class Run007RedditPanel {
    public static void main(String[] args) {
        WebLookAndFeel.install();

        new RedditPanel();
    }
}
