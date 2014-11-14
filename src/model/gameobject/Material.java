package model.gameobject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public abstract class Material extends ShipPart {
    
 
    
    public Material(String name , int value , int weight , int durability , BufferedImage image) {
        super(name , value , weight , durability , image);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(getImage(), null, 0, 0);
    }
       
}
