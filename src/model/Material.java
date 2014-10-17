package model;

import java.awt.image.BufferedImage;

public abstract class Material extends ShipPart {
    private String imagePath;
    
    public void setImage(String imagePath)
    {
        this.imagePath = imagePath;
    }
    
    public String getImage()
    {
        return this.imagePath;
    }
       
}
