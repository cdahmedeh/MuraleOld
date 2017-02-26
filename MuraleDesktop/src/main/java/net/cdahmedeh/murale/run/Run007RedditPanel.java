package net.cdahmedeh.murale.run;

import com.alee.laf.WebLookAndFeel;
import net.cdahmedeh.muralelib.provider.reddit.RedditProvider;
import net.cdahmedeh.murale.ui.dialog.RedditDialog;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class Run007RedditPanel {
    public static void main(String[] args) {
        WebLookAndFeel.install();

        new RedditDialog(new RedditProvider());
    }
}
