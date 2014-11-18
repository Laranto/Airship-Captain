package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.ShipPart;
import common.Constants;
import common.enums.PropertyEnum;
import controller.WindowController;

public class ConstructionStrategy extends HandlerStrategy {

    private Airship airship;
    private ShipPart activePlacement;
    private boolean isActive = false;
    /**
     * Flag if the user is moving an entity
     */
    private boolean isMoveAction = false;
    
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
                    System.out.println(((Entity)activePlacement).getOrientation());
                    this.airship.placeEntity((Entity)activePlacement, tileX, tileY);
                }
            }
        }

    }

    @Override
    public void publishProperty(Object publishedProperty) {
    	if(publishedProperty instanceof PropertyEnum){
    		if(((PropertyEnum)publishedProperty) == PropertyEnum.SAVE){
    			//TODO check if airship is valid if ok-> werft false -> message user (use JOptionPane)
    			WindowController.showHarbor(airship);
    		}
    	}
        if(publishedProperty instanceof ShipPart){
            this.activePlacement = (ShipPart) publishedProperty;
            if(activePlacement instanceof Entity){
                resetOrientation((Entity)activePlacement);
            }
        }
        else{
            this.activePlacement=null;
        }
        isActive = true;
    }

    private void resetOrientation(Entity ent) {
        ent.setOrientation(Constants.ENTITY_ORIENTATION_RIGHT);
    }

    @Override
    public void keyEvent(KeyEvent e) {
        if(activePlacement instanceof Entity){
            Entity ent = (Entity) activePlacement;
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                ent.setOrientation(Constants.ENTITY_ORIENTATION_LEFT);
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                ent.setOrientation(Constants.ENTITY_ORIENTATION_RIGHT);
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                ent.setOrientation(Constants.ENTITY_ORIENTATION_UP);
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                ent.setOrientation(Constants.ENTITY_ORIENTATION_DOWN);
            }
            System.out.println(((Entity)activePlacement).getOrientation());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public ShipPart getActivePlacement() {
        return activePlacement;
    }
}
