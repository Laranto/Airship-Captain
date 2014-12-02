package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.GameState;
import model.navigation.Harbor;
import model.navigation.Route;

import common.Constants;
import common.enums.MenuItemEnum;

import controller.WindowController;

public class NavigationStrategy extends HandlerStrategy implements Tickable {

    private Harbor toHarbor;
    private Route route;
    
    public NavigationStrategy(){
        route = new Route(GameState.getInstance().getCurrentHarbor());
        WindowController.getTickables().add(this);
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
    public void mouseEvent(MouseEvent event) {
        if(toHarbor != null && GameState.getInstance().getCurrentHarbor().getPosition() != toHarbor.getPosition() && route.getRemainingDuration() == 0){
            if(wantsToTravel()){
            	route.setTo(toHarbor);
                route.start();
            }
        }
    }
                    
                    
    private boolean wantsToTravel() {
        return WindowController.showTravelConfirmation("Reisen?", "Diese Reise dauert "+route.getDuration()+" Sekunden, willst du jetzt segeln?") == 1;
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
    	if(!route.isTravelling()){
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


    @Override
    public void tick() {
        if(route.getRemainingDuration() != 0 && !route.hasActiveScenario()){
            route.travel();
        }else{
//            System.out.println("not Traveling");
        }
    }
}