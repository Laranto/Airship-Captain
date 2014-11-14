package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.entity.Weapon;
import common.Constants;

public class FightStrategy extends HandlerStrategy {

    private Airship airship;
    private Entity activeEntity;
    
    public FightStrategy(){
        this(new Airship());
    }
    
    public FightStrategy(Airship airship){
        this.airship = airship;
    }
    
    @Override
    public void  mouseEvent(MouseEvent e) {
        int tileX = e.getX() / Constants.TILE_SIZE;
        int tileY = e.getY() / Constants.TILE_SIZE;
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES){
            if(activeEntity == null){
                activeEntity = airship.getEntity(tileX, tileY);
            }else{
                if(activeEntity instanceof Weapon){
                    Weapon w = (Weapon)activeEntity;
                    w.aim(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void publishProperty(Object activeEntity) {
        this.activeEntity = (Entity) activeEntity;
    }

    @Override
    public void keyEvent(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
