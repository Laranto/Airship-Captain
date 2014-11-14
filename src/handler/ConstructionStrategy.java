package handler;

import java.awt.event.MouseEvent;

import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.ShipPart;

import common.Constants;

public class ConstructionStrategy extends HandlerStrategy {

    private Airship airship;
    private ShipPart activePlacement;
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
            
            if(activePlacement == null){
                this.airship.removeMaterial(tileX, tileY);
            }else if (tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES) {
                if(activePlacement instanceof Material){
                    this.airship.placeMaterial((Material)activePlacement, tileX, tileY);
                }
                if(activePlacement instanceof Entity){
                    this.airship.placeEntity((Entity)activePlacement, tileX, tileY);
                }
            }
        }

    }

    @Override
    public void publishProperty(Object activeMaterial) {
        if(activeMaterial instanceof ShipPart){
            this.activePlacement = (ShipPart) activeMaterial;
        }
        else{
            this.activePlacement=null;
        }
        isActive = true;
    }
}
