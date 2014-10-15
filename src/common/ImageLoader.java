package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    public static BufferedImage loadImage(File imgFile) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            System.err.println("Tried reading file " + imgFile.getAbsolutePath());
            e.printStackTrace();
        }
        return img;
    }
}
