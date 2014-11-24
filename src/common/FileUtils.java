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

import javax.imageio.ImageIO;

import model.GameState;
import model.gameobject.Airship;

public class FileUtils {
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
            ois.readObject();
            
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
