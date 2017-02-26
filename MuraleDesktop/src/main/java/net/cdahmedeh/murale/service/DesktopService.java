package net.cdahmedeh.murale.service;

import net.cdahmedeh.muralelib.domain.Configuration;
import net.cdahmedeh.muralelib.domain.Wallpaper;
import net.cdahmedeh.muralelib.util.net.NetTools;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class DesktopService {
    public static String EXEC = "bin\\MuraleWinCommand.exe";
    private static final String SAVED_WALLPAPERS_LOCATION = System.getProperty("user.home") + "/.murale/wallpapers/";


    public static void setWallpaper(Wallpaper wallpaper, Configuration configuration) {
        try {
            File downloadFile = new File(SAVED_WALLPAPERS_LOCATION + wallpaper.getUuid() + ".jpg");

            downloadWallpaper(wallpaper, downloadFile);

            wallpaper.setLocation(downloadFile.getAbsolutePath());

            ProcessBuilder builder = new ProcessBuilder(EXEC, "-s", "crop-to-fit", "-f", wallpaper.getLocation());
            Process process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadWallpaper(Wallpaper wallpaper, File downloadFile) throws IOException {
        HttpGet request = new HttpGet(wallpaper.getUrl());

        request.setHeader("Referer", wallpaper.getOrigin());

        CloseableHttpClient client = NetTools.createHttpClient();
        CloseableHttpResponse response = client.execute(request);

        FileUtils.copyInputStreamToFile(response.getEntity().getContent(), downloadFile);
    }
}