package net.cdahmedeh.murale.ui.starter;

import com.alee.laf.WebLookAndFeel;
import com.google.common.eventbus.EventBus;
import net.cdahmedeh.murale.ui.frame.MainFrame;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public class UIStarter {
    public static void main(String[] args) {
        WebLookAndFeel.install();

        new MainFrame();
    }
}
