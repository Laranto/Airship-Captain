package handler;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.economy.Market;
import model.gameobject.Airship;
import model.navigation.Harbor;
import model.navigation.Route;

import common.Constants;
import common.enums.MenuItemEnum;

import controller.WindowController;

public class NavigationStrategy extends HandlerStrategy {

    private Airship airship;
    private Harbor currentHarbor;
    private Harbor toHarbor;
    private List<Harbor> harbors;
    private Route route;
    
    public NavigationStrategy(){
        this(new Airship());
    }
    
    public NavigationStrategy(Airship airship){
        this.airship = airship;
        parseHarbours();
        route = new Route(currentHarbor);
    }
    
    private void parseHarbours() {
        currentHarbor =  new Harbor(new Point(310, 340));
        harbors = new ArrayList<Harbor>();
        harbors.add(new Harbor(new Market(), new Point(110, 500), false));
        harbors.add(new Harbor(new Market(), new Point(160, 410), false));
        harbors.add(new Harbor(new Market(), new Point(210, 300), false));
        harbors.add(new Harbor(new Market(), new Point(280, 200), false));
        harbors.add(new Harbor(new Market(), new Point(290, 60), false));
        harbors.add(new Harbor(new Market(), new Point(280, 400), false));
        harbors.add(new Harbor(new Market(), new Point(300, 490), false));
        harbors.add(new Harbor(new Market(), new Point(330, 260), false));
        harbors.add(new Harbor(new Market(), new Point(450, 200), false));
        harbors.add(new Harbor(new Market(), new Point(460, 500), false));
        harbors.add(new Harbor(new Market(), new Point(490, 290), false));
        harbors.add(new Harbor(new Market(), new Point(500, 90), false));
        harbors.add(new Harbor(new Market(), new Point(500, 400), false));
        harbors.add(new Harbor(new Market(), new Point(600, 50), false));
        harbors.add(new Harbor(new Market(), new Point(600, 270), false));
        harbors.add(new Harbor(new Market(), new Point(600, 345), false));
        harbors.add(new Harbor(new Market(), new Point(610, 200), false));
        harbors.add(new Harbor(new Market(), new Point(720, 275), false));
        harbors.add(currentHarbor);
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
        
    }

    @Override
    public void keyEvent(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void publishProperty(Object next) {
        if(next instanceof MenuItemEnum){
            if(MenuItemEnum.HARBOR == (MenuItemEnum)next){
                WindowController.showHarbor(airship);
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