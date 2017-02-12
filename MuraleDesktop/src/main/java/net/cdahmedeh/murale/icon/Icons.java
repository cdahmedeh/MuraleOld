package net.cdahmedeh.murale.icon;

import com.google.common.io.Resources;
import net.cdahmedeh.murale.error.ResourceMissingException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cdahmedeh on 2/4/2017.
 */
public class Icons {
    /**
     * Retrieves a Swing Image from the icon set found in the resources/icons
     * folder.
     *
     * It will return null if the file does not exist or cannot be loaded.
     *
     * @param iconName The name of the icon without the file extension or folder.
     * @return An Image reference to the created image.
     */
    public static ImageIcon getIcon(String iconName) {
        URL imgURL = Resources.getResource("icons/" + iconName + ".png");

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            throw new ResourceMissingException("Could not find icon " + iconName);
        }
    }

    /**
     * Retrieves a SWT Image from the icon set found in the resources/icons
     * folder.
     *
     * It will return null if the file does not exist or cannot be loaded.
     *
     * @param iconName The name of the icon without the file extension or folder.
     * @return An Image reference to the created image.
     */
    public static Image getSwtIcon(String iconName) {
        URL imgURL = Resources.getResource("icons/" + iconName + ".png");

        try {
            if (imgURL != null) {
                return new Image(null, imgURL.openStream());
            } else {
                throw new ResourceMissingException("Could not find icon " + iconName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
