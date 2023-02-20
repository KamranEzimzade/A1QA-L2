package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static aquality.selenium.browser.AqualityServices.getLogger;

public class DownloadPhotoUtil {

    private static final String DownloadedPhotoPath = "src/main/resources/photo.jpg";

    public static String downloadPhoto(String photoURL) throws IOException {
        InputStream in = new URL(photoURL).openStream();
        Files.copy(in, Paths.get(new File(DownloadedPhotoPath).getAbsolutePath()));
        getLogger().info("Image downloaded from " + photoURL);
        return DownloadedPhotoPath;
    }
}
