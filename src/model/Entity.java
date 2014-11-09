package model;

import java.awt.geom.Point2D;
import java.util.Vector;


public abstract class Entity extends ShipPart{
    /**
     * 2d Vector holding the orientation and dimension of the entity
     */
    private Vector<Integer> orientation;
    
    
    /**
     * Position where the Startposition of the entity is placed.
     */
    private Point2D position;
    
    
    public void rotateLeft(){
        Integer tmp = orientation.get(0);
        orientation.set(0, orientation.get(1));
        orientation.set(1, tmp*-1);
    }
    public void rotateRight(){
        Integer tmp = orientation.get(0);
        orientation.set(0, orientation.get(1)*-1);
        orientation.set(1, tmp);
    }
}
