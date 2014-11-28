package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.GameState;
import model.navigation.Harbor;
import model.navigation.Route;

import common.Constants;
import common.Utils;
import common.enums.MenuItemEnum;

import controller.WindowController;

public class NavigationStrategy extends HandlerStrategy {

    private Harbor toHarbor;
    private Route route;
    
    public NavigationStrategy(){
        route = new Route(GameState.getInstance().getCurrentHarbor());
    }
    
    
    public Harbor getHarbor(int tileX, int tileY){
        for(Harbor h: Constants.HARBORS){
            if(isOnHarbor(h, tileX, tileY)){
                return h;
            }
        }
        return null;
    }

    private boolean isOnHarbor(Harbor h, int tileX, int tileY) {
        return h.getPosition().getX() <= tileX && tileX <= h.getPosition().getX()+Constants.HARBOR_CIRCLE_DIAMETER &&
           h.getPosition().getY() <= tileY && tileY <= h.getPosition().getY()+Constants.HARBOR_CIRCLE_DIAMETER;
    }
    
    public boolean hasNextDestination(){
        return toHarbor != null;
    }
    
    public Harbor getNextDestination(){
        return toHarbor;
    }
    
    public Route getRoute(){
        return route;
    }
    
    @Override
    public void mouseEvent(MouseEvent e) {
        Harbor currentHarbor = GameState.getInstance().getCurrentHarbor();
        if(toHarbor != null && currentHarbor.getPosition() != toHarbor.getPosition()){
            
            double distance =   Math.sqrt( (Math.pow( toHarbor.getPosition().getX()-currentHarbor.getPosition().getX(),2)
                                +Math.pow( toHarbor.getPosition().getY()-currentHarbor.getPosition().getY(),2) ));
            
            int timeToTravel = (int)( distance / GameState.getInstance().getAirship().getSpeed() )  ;
            
            if(WindowController.showTravelConfirmation("Reisen?", "Diese Reise dauert "+timeToTravel+" Sekunden, willst du jetzt segeln?") == 1)
            {
                double battleChance = 0.1;

                GameState.getInstance().getCurrentHarbor().setActive(false);
                toHarbor.setActive(true);
                toHarbor.setNextDestination(false);
                GameState.getInstance().setCurrentHarbor(toHarbor);
                route = new Route();
                
                while(timeToTravel > 0)
                {
                    int fightProbabilty = Utils.getRandomIntBetween(0, 100);
                    
                    if(battleChance*100 > fightProbabilty)
                    {
                        System.out.println("FIGHT!!");
                        battleChance = 0.1;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    
                    //TODO replace these numbers with an constant
                    battleChance*=1.2;
                    
                    timeToTravel--;
                }
            }
        }
    }

    @Override
    public void keyEvent(KeyEvent e) {
        
    }

    @Override
    public void publishProperty(Object next) {
        if(next instanceof MenuItemEnum){
            if(MenuItemEnum.HARBOR == (MenuItemEnum)next){
                WindowController.showHarbor();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(toHarbor == null && (toHarbor = getHarbor(e.getX(), e.getY())) != null){
            toHarbor.setNextDestination(true);
            route.setTo(toHarbor);
        }
        if(toHarbor != null && !isOnHarbor(toHarbor, e.getX(), e.getY())){
            toHarbor.setNextDestination(false);
            toHarbor = null;
            route.setTo(null);
        }
    }
}