package handler;

import java.awt.event.MouseEvent;

import model.Airship;
import model.Material;

import common.Constants;

public class ConstructionStrategy extends HandlerStrategy {

    private Airship airship;
    private Material activeMaterial;
    private boolean isActive = false;
    
    public ConstructionStrategy(){
        this(new Airship());
    }
    
    public ConstructionStrategy(Airship airship){
        this.airship = airship;
    }
    
    @Override
    public void  mouseEvent(MouseEvent e) {
        if(isActive){
            int tileX = e.getX() / Constants.TILE_SIZE;
            int tileY = e.getY() / Constants.TILE_SIZE;
            
            if(activeMaterial == null){
                this.airship.removeMaterial(tileX, tileY);
            }else if (tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES) {
                this.airship.placeMaterial(activeMaterial, tileX, tileY);
            }
        }

    }

    @Override
    public void publishProperty(Object activeMaterial) {
        this.activeMaterial = (Material) activeMaterial;
        isActive = true;
    }
}
