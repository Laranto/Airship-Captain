package model.navigation;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import common.Constants;

import model.gameobject.Renderable;


public class Route implements Renderable{

    private Harbor from;
    private Harbor to;
    private Shape line;
    
    public Route(Harbor from){
        this(from, null);
    }
    
    public Route(Harbor from , Harbor to){
        this.from = from;
        this.to = to;
    }
    
    public Harbor getFrom(){
        return from;
    }
    
    public Harbor getTo(){
        return to;
    }
    
    public void setTo(Harbor harbor){
        to = harbor;
    }
    
    public Double getDistance(){
        if(!isCorrectRoute()){
            return 0.0;
        }
        int x1 = (int) from.getPosition().getX(),
            x2 = (int) to.getPosition().getX(),
            y1 = (int) from.getPosition().getY(),
            y2 = (int) to.getPosition().getY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    private boolean isCorrectRoute() {
        return from != null && to != null;
    }

    @Override
    public void render(Graphics2D g) {
        if(isCorrectRoute()){
            int radius = (int)Constants.HARBOR_CIRCLE_DIAMETER/2;
            line = new Line2D.Double((int)from.getPosition().getX()+radius,
                    (int)from.getPosition().getY()+radius,
                    (int)to.getPosition().getX()+radius,
                    (int)to.getPosition().getY()+radius);
            g.draw(line);
            from.render(g);
            to.render(g);
        }
    }

    public void removeLines(Graphics2D g) {
        if(!isCorrectRoute() && line != null){
            g.clearRect(
                    (int)line.getBounds().getX(), 
                    (int)line.getBounds().getY(), 
                    (int)line.getBounds().getWidth(),
                    (int)line.getBounds().getHeight());
            line = null;
        }
    }
}
