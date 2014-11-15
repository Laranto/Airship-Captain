package model.navigation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.economy.Market;

public class Harbor {
    
    private Market market;
    private Point position;
    private List<Route> routes;
    
    public Harbor(Point position){
        new Harbor(new Market(), position, new ArrayList<Route>());
    }
    
    public Harbor(Market market, Point position, List<Route> routes){
        this.market = market;
        this.position = position;
        this.routes = routes;
    }
    
    public Market getMarket(){
        return market;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public List<Route> getRoutes(){
        return routes;
    }
}
