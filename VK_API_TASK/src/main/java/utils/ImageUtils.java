package utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {

    public static void deleteFile(String name) {
        File file = new File(name);
        if(file.exists()) {
            file.delete();
        }
    }

    public static double compareImages(String pathOne, String pathTro) {
        BufferedImage image1 = ImageComparisonUtil.readImageFromResources(pathOne);
        BufferedImage image2 = ImageComparisonUtil.readImageFromResources(pathTro);

        ImageComparisonResult imageComparisonResult = new ImageComparison(image1, image2).compareImages();

        return imageComparisonResult.getDifferencePercent();
    }
}
