package handler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

import model.GameState;
import model.gameobject.Entity;
import model.gameobject.entity.Cannonball;
import model.gameobject.entity.Weapon;
import model.navigation.Scenario;
import common.Constants;
import common.enums.MenuItemEnum;
import controller.WindowController;

public class FightStrategy extends HandlerStrategy {

    private Entity activeEntity;
    private boolean isCannonActive = false;
    private static LinkedList<Cannonball> cannonballs = new LinkedList<Cannonball>();
	private Scenario scenario;
    
    public FightStrategy(Scenario scenario){
		this.scenario = scenario;
    }
    
    @Override
    public void  mouseEvent(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e) && isCannonActive){
            isCannonActive = false;
            cannonballs.removeLast();
            return;
        }
        int tileX = e.getX() / Constants.TILE_SIZE;
        int tileY = e.getY() / Constants.TILE_SIZE;
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES){
            if(activeEntity == null){
                activeEntity = GameState.getInstance().getAirship().getEntity(tileX, tileY);
            }
            if(activeEntity instanceof Weapon){
                cannonballs.addLast(((Weapon)activeEntity).aim());
                isCannonActive = true;
            }
        }else{
            if(isCannonActive){
                cannonballs.getLast().fire();
                ((Weapon)activeEntity).reload();
                activeEntity = null;
                isCannonActive = false;
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
    
    @SuppressWarnings("incomplete-switch")
    private void handleAction(MenuItemEnum activeMenuItem)
    {
        switch(activeMenuItem)
        {
        case EXIT_FIGHT:
            System.out.println("escape from battle");
            scenario.setActive(false);
            WindowController.showNavigation();
            break;
        case ROTATE_SHIP:
            GameState.getInstance().getAirship().rotate();
            break;
        }
        
    }

    @Override
    public void keyEvent(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(isCannonActive){
            int tileX = e.getX();
            int tileY = e.getY();
            cannonballs.getLast().aim(new Point(tileX, tileY));
        }
    }
    
    public static LinkedList<Cannonball> getCannonballs(){
        return cannonballs;
    }
    
    public static void removeCannonball(Cannonball cannonball){
        cannonballs.remove(cannonball);
    }
}
