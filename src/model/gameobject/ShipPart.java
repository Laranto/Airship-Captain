package model.gameobject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import common.FileUtils;


public abstract class ShipPart extends GameObject implements Renderable {
    private static final long serialVersionUID = 1L;
    /**
     * The Shipparts health
     */
    private int durability;
    private String imagePath;
    
    
    public ShipPart(String name , int value , int weight , int durability , String imagePath) {
        super(name , value , weight);
        this.durability = durability;
        try {
            setImage(imagePath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    
    /**
     * Hard sets the image. Use with care
     */
    public void setImage(BufferedImage image){
        this.image=image;
    }
    
    /**
     * Gets the image using the provided path.
     */
    public void setImage(String imagePath) throws IOException
    {
        if(imagePath!=null){
            this.imagePath=imagePath;
            this.setImage(FileUtils.loadImage(new File(imagePath)));
        }
    }
    
    public BufferedImage getImage()
    {
        if(image==null){
            try {
                setImage(imagePath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return this.image;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(getImage(), null, 0, 0);
    }
}
