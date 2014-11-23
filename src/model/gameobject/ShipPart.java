package model.gameobject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import common.ImageLoader;


public abstract class ShipPart extends GameObject implements Renderable {
    private static final long serialVersionUID = 1L;
    /**
     * The Shipparts health
     */
    private int durability;
    
    
    
    public ShipPart(String name , int value , int weight , int durability , BufferedImage image) {
        super(name , value , weight);
        this.durability = durability;
        this.image = image;
    }

    /**
     * Graphical Representation of the Part
     */
    private transient BufferedImage image;

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
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(getImage(), null, 0, 0);
    }
}
