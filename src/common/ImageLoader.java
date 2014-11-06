package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    /**
     * Contained unit to load a Bufferedimage from a file 
     * @param imgFile
     * @return
     * @throws IOException 
     */
    public static BufferedImage loadImage(File imgFile) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            throw new IOException("Tried reading file " + imgFile.getAbsolutePath(),e);
            
        }
        return img;
    }
}
