package model.navigation;


public class Route {

    private Harbor from;
    private Harbor to;
    
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
    
    public Double getDistance(){
        int x1 = (int) from.getPosition().getX(),
            x2 = (int) to.getPosition().getX(),
            y1 = (int) from.getPosition().getY(),
            y2 = (int) to.getPosition().getY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}
