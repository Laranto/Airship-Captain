package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.GameState;
import model.gameobject.Entity;
import model.gameobject.entity.Weapon;
import common.Constants;
import common.enums.MenuItemEnum;
import controller.WindowController;

public class FightStrategy extends HandlerStrategy {

    private Entity activeEntity;
    
    public FightStrategy(){
    }
    
    @Override
    public void  mouseEvent(MouseEvent e) {
        int tileX = e.getX() / Constants.TILE_SIZE;
        int tileY = e.getY() / Constants.TILE_SIZE;
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES){
            if(activeEntity == null){
                activeEntity = GameState.getInstance().getAirship().getEntity(tileX, tileY);
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
        if(activeEntity instanceof MenuItemEnum)
        {
            this.handleAction((MenuItemEnum) activeEntity);
        }else{
            this.activeEntity = (Entity) activeEntity;
        }
    }
    
    private void handleAction(MenuItemEnum activeMenuItem)
    {
        switch(activeMenuItem)
        {
        case EXIT_FIGHT:
            System.out.println("escape from battle");
            WindowController.showNavigation();
            break;
        case ROTATE_SHIP:
            System.out.println("Schiff wenden");
            break;
        }
        
    }

    @Override
    public void keyEvent(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
