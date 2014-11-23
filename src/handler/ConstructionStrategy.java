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
    private Object activePlacement;
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
            if(isMoveAction){
                endMove(tileX, tileY);
            }else if(activePlacement instanceof PropertyEnum){
                PropertyEnum active = (PropertyEnum) activePlacement;
                if(active==PropertyEnum.DELETE_MATERIAL){
                    this.airship.removeMaterial(tileX, tileY);
                }else if(active==PropertyEnum.DELETE_ENTITY){
                    this.airship.removeEntity(tileX,tileY);
                }else if(active==PropertyEnum.MOVE){
                    startMove(tileX,tileY);
                }
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

    
    private void startMove(int tileX,int tileY) {
        if(!isMoveAction){
            Entity selected = airship.removeEntity(tileX, tileY);
            if(selected!=null){
                isMoveAction=true;
                activePlacement = selected;
            }
        }
    }
    
    private void endMove(int tileX,int tileY){
        if(airship.placeEntity((Entity)activePlacement, tileX, tileY)){
            isMoveAction=false;
            publishProperty(PropertyEnum.MOVE);
        }
    }
    
    private void undoMove(){
        if(activePlacement instanceof Entity){
            isMoveAction=false;
            Entity ent = (Entity) activePlacement;
            airship.placeEntity(ent, ent.getPosition().x, ent.getPosition().y);
        }
    }
    

    @Override
    public void publishProperty(Object publishedProperty) {
        if(isMoveAction){
            undoMove();
        }
        
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
        }else if(publishedProperty instanceof PropertyEnum){
            this.activePlacement =  publishedProperty;
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
            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_DOWN){
                ent.rotate();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public Object getActivePlacement() {
        return activePlacement;
    }
}
