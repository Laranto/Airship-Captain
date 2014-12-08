package handler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingUtilities;

import model.GameState;
import model.gameobject.Entity;
import model.gameobject.entity.Weapon;
import model.gameobject.entity.weapon.Aim;
import model.gameobject.entity.weapon.Cannonball;
import model.navigation.Scenario;

import common.Constants;
import common.Math.Vector2d;
import common.enums.MenuItemEnum;

import controller.WindowController;

/**
 * Handles the inputs when a fight starts
 */
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
            activeEntity = null;
            isCannonActive = false;
            aim=null;
        }
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES){
            if(activeEntity == null){
                activeEntity = GameState.getInstance().getAirship().getEntity(tileX, tileY);
            }
            if(activeEntity instanceof Weapon){
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
            WindowController.getTickables().remove(this);
            GameState.getInstance().getAirship().resetRotation();
            WindowController.showNavigation();
            break;
        case ROTATE_SHIP:
            GameState.getInstance().getAirship().rotate();
            break;
        }
        
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

    @Override
    public void tick() {
        for (int i = 0;i<cannonballs.size();i++) {
            Cannonball cannonball = cannonballs.get(i);
            if(cannonball!=null){
                cannonball.move(GameState.getInstance().getAirship(),scenario.getEnemy());
                if(cannonball.getDamage()==0 || cannonball.isOutOfBounds()){
                    cannonballs.remove(i);
                }
            }
        }

        handleBattleSituation();
        handleArtificialFiring();
    }
    
    
    private void handleBattleSituation()
    {        
        if(scenario.getEnemy().calculateDamageInPercent() > Constants.BATTLE_FINISH_RATIO){
            int profit = (scenario.getEnemy().getTotalDurability())/Constants.BATTLE_PROFIT_RATIO;
            scenario.getEnemy().setTotalDurability(0);
            GameState.getInstance().getAirship().getCaptain().getMoney().addAmount(profit);
            WindowController.showMessage("Kampf vorbei", "Dein Gegner ist geflohen. Du hast $ "+profit+" gewonnen. ");
            System.out.println(cannonballs.size());
            handleAction(MenuItemEnum.EXIT_FIGHT);
        }
        
        if(GameState.getInstance().getAirship().calculateDamageInPercent() > Constants.BATTLE_FINISH_RATIO){
            int loss = (GameState.getInstance().getAirship().getTotalDurability())/Constants.BATTLE_PROFIT_RATIO;
            GameState.getInstance().getAirship().setTotalDurability(0);
            GameState.getInstance().getAirship().getCaptain().getMoney().removeAmount(loss);
            WindowController.showMessage("Kampf vorbei", "Du hast $ "+loss+" verloren.. :( ");
            System.out.println(cannonballs.size());
            handleAction(MenuItemEnum.EXIT_FIGHT);
        }
    }


    private void handleArtificialFiring()
    {
        Random random = new Random();
        ArrayList<Weapon> weapons = scenario.getEnemy().getWeapons();

        int aimX = random.nextInt(Constants.AIRSHIP_WIDTH_TILES*Constants.TILE_SIZE);
        int aimY = random.nextInt(Constants.AIRSHIP_HEIGHT_TILES*Constants.TILE_SIZE);
        
        if(GameState.getInstance().getAirship().getShipPartByPosition(aimX/Constants.TILE_SIZE, aimY/Constants.TILE_SIZE) != null)
        {
            int weaponNumber = random.nextInt(weapons.size());
            weapons.get(weaponNumber).aim(aimX, aimY).shiftFrom(new Vector2d(Constants.AIRSHIP_WIDTH_TILES*Constants.TILE_SIZE, 0));
            cannonballs.add(weapons.get(weaponNumber).fire());
        }
    }

    public Aim getAim() {
        return aim;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameState.getInstance().getAirship().getCaptain().move(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
