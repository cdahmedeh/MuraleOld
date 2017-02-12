package net.cdahmedeh.murale.ui.tray;

import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.muralelib.logging.Logging;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import static net.cdahmedeh.murale.app.AppConstants.APP_NAME;

/**
 * Tray Icon Handler for Murale. Uses SWT for better L&F
 *
 * Created by cdahmedeh on 2/8/2017.
 */
public class AppTrayIcon {

    private TrayItem trayItem;

    public static void show() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new AppTrayIcon();
            }
        });
        thread.start();
    }

    public AppTrayIcon() {
        Display display = new Display();
        Shell shell = new Shell (display);

        Image icon = Icons.getSwtIcon("murale-16");
        setupTrayIcon(display, icon);
        setupTrayMenu(shell);

        shell.setBounds(0, 0, 1, 1);
        shell.open ();
        shell.setVisible(false);
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }

        icon.dispose();
        display.dispose();
    }

    private void setupTrayIcon(Display display, Image icon) {
        final Tray tray = display.getSystemTray ();

        if (tray == null) {
            Logging.warn("No system tray is available on this system");
        } else {
            trayItem = new TrayItem(tray, SWT.NONE);
            trayItem.setToolTipText(APP_NAME);
            trayItem.setImage(icon);

            trayItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    AppContext.getMainFrame().toggleVisible();
                }
            });
        }
    }

    private void setupTrayMenu(Shell shell) {
        final Menu menu = new Menu (shell, SWT.POP_UP);
        if (trayItem != null) {
            trayItem.addListener(SWT.MenuDetect, event -> menu.setVisible(true));
        }

        MenuItem nextWallpaperItem = new MenuItem(menu, SWT.PUSH);
        nextWallpaperItem.setText("Next Wallpaper");
        nextWallpaperItem.setImage(Icons.getSwtIcon("next"));
        nextWallpaperItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppContext.getWallpaperFlow().nextWallpaper();
            }
        });

        new MenuItem(menu, SWT.SEPARATOR);

        MenuItem hideShowMuraleItem = new MenuItem(menu, SWT.PUSH);
        hideShowMuraleItem.setText("Hide/Show Murale");
        hideShowMuraleItem.setImage(Icons.getSwtIcon("hide"));
        hideShowMuraleItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppContext.getMainFrame().toggleVisible();
            }
        });

        MenuItem exitMurale = new MenuItem(menu, SWT.PUSH);
        exitMurale.setText("Exit Murale");
        exitMurale.setImage(Icons.getSwtIcon("exit"));
        exitMurale.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AppContext.exit();
            }
        });
    }
}
