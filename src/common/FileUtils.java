package common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.GameState;

public class FileUtils {
    
    private static HashMap<String, BufferedImage> loadedImages = new HashMap<>();
    
    
    /**
     * Gets an image using the provided path. It tries first to read it from the memory, 
     * if there is no instance of the image in the memory it will be loaded from the file system.
     * @param imagePath Path where the image file is, including image name
     * @return a buffered image version of the file provided
     * @throws IOException when the file could not have been read.
     */
    public static BufferedImage getImage(String imagePath) throws IOException{
        BufferedImage loadedImage = loadedImages.get(imagePath);
        if(loadedImage==null){
            loadedImage = loadImage(new File(imagePath));
            loadedImages.put(imagePath, loadedImage);
        }
        return loadedImage;
    }
    
    /**
     * Contained unit to load a Bufferedimage from a file. If possible use <code>getImage()</code>
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
    
    public static boolean saveObjectFile(Serializable object, String path, String fileName, String extention){
        return saveObjectFile(object, path + File.separator+fileName+"."+extention);
    }
    public static boolean saveObjectFile(Serializable object, String location){
        OutputStream fileOutputStream = null;
        ObjectOutputStream oos = null;

        try {
            fileOutputStream = new FileOutputStream(location);
            oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(GameState.getInstance().getAirship());
            oos.flush();
            

            return true;
        } catch (IOException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                oos.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Serializable loadObjectFile(String path, String fileName, String extention){
        return loadObjectFile(path + File.separator+fileName+"."+extention);
    }

    private static Serializable loadObjectFile(String location) {
        InputStream fileInputStream = null;
        ObjectInputStream ois = null;
        Serializable readObject = null;
        
        try {
            fileInputStream = new FileInputStream(location);

            /*
             * The objects which should be loaded
             */
            ois = new ObjectInputStream(fileInputStream);
            readObject = (Serializable) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return readObject;
    }
}
