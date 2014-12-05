package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.GameState;
import model.gameobject.Entity;
import model.gameobject.GameObject;
import model.gameobject.Material;
import model.gameobject.ShipPart;
import common.Constants;
import common.enums.PropertyEnum;
import controller.WindowController;

public class ConstructionStrategy extends HandlerStrategy {

    private Object activePlacement;
    private boolean isActive = false;
    /**
     * Flag if the user is moving an entity
     */
    private boolean isMoveAction = false;
    
    @Override
    public void  mouseEvent(MouseEvent e) {
        if(isActive){
            int tileX = e.getX() / Constants.TILE_SIZE;
            int tileY = e.getY() / Constants.TILE_SIZE;
            if(isMoveAction){
                endMove(tileX, tileY);
            }else if(activePlacement instanceof PropertyEnum){
                PropertyEnum active = (PropertyEnum) activePlacement;
                
                GameObject removedObject = null;
                if(active==PropertyEnum.DELETE_MATERIAL){
                    removedObject = GameState.getInstance().getAirship().removeMaterial(tileX, tileY);
                }else if(active==PropertyEnum.DELETE_ENTITY){
                    removedObject = GameState.getInstance().getAirship().removeEntity(tileX,tileY);
                }else if(active==PropertyEnum.MOVE){
                    startMove(tileX,tileY);
                }
                
                if(removedObject != null)
                {
                    GameState.getInstance().getAirship().getCaptain().getMoney().addAmount(removedObject.getValue()*Constants.OBJECT_SELL_RATIO);
                }
                
            }else if (tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES) {
                try{
                    if(activePlacement instanceof Material){
                        GameState.getInstance().getAirship().placeMaterial((Material)activePlacement, tileX, tileY);
                    }
                    if(activePlacement instanceof Entity){
                        GameState.getInstance().getAirship().placeEntity((Entity)activePlacement, tileX, tileY);
                    }
                }catch(ArithmeticException ex){
                    WindowController.showError("Not enough money", "You don't have enough money to place this object.");
                }
            }
        }

    }

    
    private void startMove(int tileX,int tileY) {
        if(!isMoveAction){
            Entity selected = GameState.getInstance().getAirship().removeEntity(tileX, tileY);
            if(selected!=null){
                isMoveAction=true;
                activePlacement = selected;
            }
        }
    }
    
    private void endMove(int tileX,int tileY){
        if(GameState.getInstance().getAirship().placeEntity((Entity)activePlacement, tileX, tileY)){
            isMoveAction=false;
            publishProperty(PropertyEnum.MOVE);
        }
    }
    
    private void undoMove(){
        if(activePlacement instanceof Entity){
            isMoveAction=false;
            Entity ent = (Entity) activePlacement;
            GameState.getInstance().getAirship().placeEntity(ent, ent.getPosition().x, ent.getPosition().y);
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
    		    if(GameState.getInstance().getAirship().isJoined()){
    		        WindowController.showHarbor();
    		    }else{
    		        WindowController.showError("Fehler beim speichern","Das Luftschiff konnte so nicht gespeichert werden. Es ist nicht an einem Stück!");
    		    }
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
