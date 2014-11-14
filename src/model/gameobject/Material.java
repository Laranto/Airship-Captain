package model.gameobject;

import java.awt.Graphics2D;


public abstract class Material extends ShipPart {
    
    public Material() {
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(getImage(), null, 0, 0);
    }
       
}
