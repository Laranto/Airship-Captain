package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import common.ImageLoader;

import model.interfaces.Renderable;

public abstract class ShipPart extends GameObject implements Renderable {
    /**
     * The Shipparts health
     */
    private int durability;
    
    /**
     * Graphical Representation of the Part
     */
    private BufferedImage image;

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
    
    public void setImage(BufferedImage image){
        this.image=image;
    }
    public void setImage(String imagePath) throws IOException
    {
        this.setImage(ImageLoader.loadImage(new File(imagePath)));
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
    
}
