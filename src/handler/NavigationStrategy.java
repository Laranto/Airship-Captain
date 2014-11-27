package handler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.GameState;
import model.economy.Market;
import model.navigation.Harbor;
import model.navigation.Route;

import common.Constants;
import common.Utils;
import common.enums.MenuItemEnum;

import controller.WindowController;

public class NavigationStrategy extends HandlerStrategy {

    private Harbor currentHarbor;
    private Harbor toHarbor;
    private List<Harbor> harbors;
    private Route route;
    
    public NavigationStrategy(){
        parseHarbours();
        route = new Route(currentHarbor);
    }
    
    private void parseHarbours() {
        currentHarbor =  GameState.getInstance().getCurrentHarbor();
        harbors = new ArrayList<Harbor>();
        harbors.add(new Harbor(new Market(), new Point(110, 500), currentHarbor.getPosition().equals(new Point(110, 500))));
        harbors.add(new Harbor(new Market(), new Point(160, 410), currentHarbor.getPosition().equals(new Point(160, 410))));
        harbors.add(new Harbor(new Market(), new Point(210, 300), currentHarbor.getPosition().equals(new Point(210, 300))));
        harbors.add(new Harbor(new Market(), new Point(280, 200), currentHarbor.getPosition().equals(new Point(280, 200))));
        harbors.add(new Harbor(new Market(), new Point(280, 400), currentHarbor.getPosition().equals(new Point(280, 400))));
        harbors.add(new Harbor(new Market(), new Point(290,  60), currentHarbor.getPosition().equals(new Point(290,  60))));
        harbors.add(new Harbor(new Market(), new Point(300, 490), currentHarbor.getPosition().equals(new Point(300, 490))));
        harbors.add(new Harbor(new Market(), new Point(310, 340), currentHarbor.getPosition().equals(new Point(310, 340))));
        harbors.add(new Harbor(new Market(), new Point(330, 260), currentHarbor.getPosition().equals(new Point(330, 260))));
        harbors.add(new Harbor(new Market(), new Point(450, 200), currentHarbor.getPosition().equals(new Point(450, 200))));
        harbors.add(new Harbor(new Market(), new Point(460, 500), currentHarbor.getPosition().equals(new Point(460, 500))));
        harbors.add(new Harbor(new Market(), new Point(490, 290), currentHarbor.getPosition().equals(new Point(490, 290))));
        harbors.add(new Harbor(new Market(), new Point(500,  90), currentHarbor.getPosition().equals(new Point(500,  90))));
        harbors.add(new Harbor(new Market(), new Point(500, 400), currentHarbor.getPosition().equals(new Point(500, 400))));
        harbors.add(new Harbor(new Market(), new Point(600,  50), currentHarbor.getPosition().equals(new Point(600,  50))));
        harbors.add(new Harbor(new Market(), new Point(600, 270), currentHarbor.getPosition().equals(new Point(600, 270))));
        harbors.add(new Harbor(new Market(), new Point(600, 345), currentHarbor.getPosition().equals(new Point(600, 345))));
        harbors.add(new Harbor(new Market(), new Point(610, 200), currentHarbor.getPosition().equals(new Point(610, 200))));
        harbors.add(new Harbor(new Market(), new Point(720, 275), currentHarbor.getPosition().equals(new Point(720, 275))));
    }
    
    public List<Harbor> getHarbors() {
        return harbors;
    }
    
    public Harbor getHarbor(int tileX, int tileY){
        for(Harbor h: harbors){
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
        currentHarbor = GameState.getInstance().getCurrentHarbor();
        if(toHarbor != null && currentHarbor.getPosition() != toHarbor.getPosition()){
            
            double distance =   Math.sqrt( (Math.pow( toHarbor.getPosition().getX()-currentHarbor.getPosition().getX(),2)
                                +Math.pow( toHarbor.getPosition().getY()-currentHarbor.getPosition().getY(),2) ));
            
            int timeToTravel = (int)( distance / GameState.getInstance().getAirship().getSpeed() )  ;
            
            if(WindowController.showTravelConfirmation("Reisen?", "Diese Reise dauert "+timeToTravel+" Sekunden, willst du jetzt segeln?") == 1)
            {
                GameState.getInstance().getCurrentHarbor().setActive(false);
                double battleChance = 0.1;

                toHarbor.setActive(true);
                toHarbor.setNextDestination(false);
                
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