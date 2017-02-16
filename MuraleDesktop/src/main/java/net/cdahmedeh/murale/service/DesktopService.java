package net.cdahmedeh.murale.service;

import net.cdahmedeh.muralelib.domain.Configuration;
import net.cdahmedeh.muralelib.domain.Wallpaper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class DesktopService {
    public static String EXEC = "bin\\MuraleWinCommand.exe";
    private static final String SAVED_WALLPAPERS_LOCATION = System.getProperty("user.home") + "/.murale/wallpapers/";


    public static void setWallpaper(Wallpaper wallpaper, Configuration configuration) {
        try {
            File downloadFile = new File(SAVED_WALLPAPERS_LOCATION + wallpaper.getUuid() + ".jpg");
            FileUtils.copyURLToFile(new URL(wallpaper.getUrl()), downloadFile);

            wallpaper.setLocation(downloadFile.getAbsolutePath());

            ProcessBuilder builder = new ProcessBuilder(EXEC, "-s", "crop-to-fit", "-f", wallpaper.getLocation());
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}