package handler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

import model.GameState;
import model.gameobject.Entity;
import model.gameobject.entity.Weapon;
import model.gameobject.entity.weapon.Aim;
import model.gameobject.entity.weapon.Cannonball;
import model.navigation.Scenario;

import common.Constants;
import common.enums.MenuItemEnum;

import controller.WindowController;

public class FightStrategy extends HandlerStrategy implements Tickable{

    private Entity activeEntity;
    private boolean isCannonActive = false;
    private LinkedList<Cannonball> cannonballs = new LinkedList<Cannonball>();
	private Scenario scenario;
	private Aim aim;
    
    public FightStrategy(Scenario scenario){
		this.scenario = scenario;
		WindowController.getTickables().add(this);
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
        
        if(isCannonActive){
            cannonballs.add(((Weapon)activeEntity).fire());
//            cannonballs.getLast().fire();
//            ((Weapon)activeEntity).reload();
            activeEntity = null;
            isCannonActive = false;
            aim=null;
        }
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES){
            if(activeEntity == null){
                activeEntity = GameState.getInstance().getAirship().getEntity(tileX, tileY);
            }
            if(activeEntity instanceof Weapon){
//                cannonballs.addLast(((Weapon)activeEntity).aim());
                aim = ((Weapon)activeEntity).aim(e.getX(), e.getY());
                isCannonActive = true;
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
            scenario.setActive(false);
            GameState.getInstance().getAirship().resetRotation();
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
            aim.setTo(new Point(tileX, tileY));
        }
    }
    
    public LinkedList<Cannonball> getCannonballs(){
        return cannonballs;
    }
    
    public void removeCannonball(Cannonball cannonball){
        cannonballs.remove(cannonball);
    }

    @Override
    public void tick() {
        for (int i = 0;i<cannonballs.size();i++) {
            Cannonball cannonball = cannonballs.get(i);
            if(cannonball!=null){
                cannonball.move();
                if(cannonball.getDamage()==0 || cannonball.isOutOfBounds()){
                    cannonballs.remove(i);
                }
            }
        }
    }

    public Aim getAim() {
        return aim;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }


}
