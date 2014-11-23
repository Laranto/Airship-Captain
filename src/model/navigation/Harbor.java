package model.navigation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.economy.Market;
import model.gameobject.Renderable;

import common.Constants;

public class Harbor implements Renderable{
    
    private Market market;
    private Point position;
    private boolean isActive;
    private boolean nextDestination;
    
    public Harbor(Point position){
        this(new Market(), position, true);
    }
    
    public Harbor(Market market, Point position, boolean active){
        this.market = market;
        this.position = position;
        this.isActive = active;
    }
    
    public Market getMarket(){
        return market;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setNextDestination(boolean nextDestination) {
        this.nextDestination = nextDestination;
    }

    @Override
    public void render(Graphics2D g) {
        Color tmpColor = g.getColor();
        g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND);
        if(isActive){
            g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_ACTIVE);
        }else if(nextDestination){
            g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_NEXT_DESTINATION);
        }else{
            g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND);
        }
        g.fillOval(
                (int)position.getX(), 
                (int)position.getY(), 
                Constants.HARBOR_CIRCLE_DIAMETER, 
                Constants.HARBOR_CIRCLE_DIAMETER);
        g.setColor(Constants.HARBOR_CIRCLE_BORDER);
        g.drawOval(
                (int)position.getX(), 
                (int)position.getY(), 
                Constants.HARBOR_CIRCLE_DIAMETER, 
                Constants.HARBOR_CIRCLE_DIAMETER);
        g.setColor(tmpColor);
    }
}
