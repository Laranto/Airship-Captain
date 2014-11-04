package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.interfaces.Renderable;
import common.ImageLoader;

public abstract class Material extends ShipPart implements Renderable {
    private BufferedImage image;
    
    public void setImage(BufferedImage image){
        this.image=image;
    }
    public void setImage(String imagePath) throws IOException
    {
        image = ImageLoader.loadImage(new File(imagePath));
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
       
}
