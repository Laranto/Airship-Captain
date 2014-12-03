package model.gameobject.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

import model.gameobject.Renderable;

import common.Constants;

public class Cannonball implements Renderable{

    private Point from;
    private Point currentPosition;
    private Point to;
    private Vector<Double> velocity; 

    public Cannonball(Point start, int damage) {
        this.from = start;
        this.to = start;
        velocity=new Vector<>();
        velocity.add((double) (to.x-from.x));
        velocity.add((double) (to.y-from.y));
        double length=Math.sqrt(Math.pow(velocity.get(0), 2)+Math.pow(velocity.get(1), 2));
        velocity.set(0, velocity.get(0)/length*damage);
        velocity.set(1, velocity.get(1)/length*damage);
    }

    public void aim(Point to){
        this.to = to;
    }
    
    public void fire(){
        currentPosition = from;
    }
    
    public Point getFrom(){
        return from;
    }
    
    public Point getTo(){
        return to;
    }
    
    private boolean isFiring() {
        return currentPosition != null;
    }

    @Override
    public void render(Graphics2D g) {
        if(isFiring()){
            //TODO:
            //1. draw cannonball
            //2. reposition cannonball
//            if(currentPosition.equals(to)){
//                FightStrategy.removeCannonball(this);
//            }
        }
//        System.out.println(
//                "xStart: "+(int)getFrom().getX()+
//                " yStart: "+(int)getFrom().getY()+
//                " xEnd: "+((int)getTo().getX())+
//                " yEnd: "+((int)getTo().getY()));
//        g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_ACTIVE);
//        g.drawLine(
//                (int)getFrom().getX(), 
//                (int)getFrom().getY(), 
//                (int)getTo().getX(), 
//                (int)getTo().getY());
        
        int xStart = (int)getFrom().getX(),
            yStart = (int)getFrom().getY(),
            xEnd = ((int)getTo().getX()),
            yEnd = ((int)getTo().getY());
        
        System.out.println(
                "xStart: "+xStart+
                " yStart: "+yStart+
                " xEnd: "+xEnd+
                " yEnd: "+yEnd);
        
        double m =  0;
        if(yStart-yEnd != 0 || xEnd-xStart != 0){
            m = (double)(yStart-yEnd)/(xEnd-xStart);
        }
        int xDistance = Constants.WINDOW_WIDTH-xEnd;
        int yDistance = Math.min(Constants.WINDOW_HEIGHT-yEnd, yEnd);
        if(xDistance < yDistance){
            yDistance *= m;
        }else{
            xDistance *= m;
        }
        System.out.println(
                " m: "+m+
                " xDistance: "+xDistance+
                " yDistance: "+yDistance+
                " xStart new: "+xStart+
                " yStart new: "+yStart+
                " xEnd new: "+(xEnd)+
                " yEnd new: "+(yEnd));
        g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_ACTIVE);
        g.drawLine(
                xStart, 
                yStart, 
                (xEnd), 
                (yEnd));
    }

    public void move() {
        currentPosition.setLocation(currentPosition.x+velocity.get(0), currentPosition.y+velocity.get(1));
    }
}
