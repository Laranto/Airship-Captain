package model;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;

import model.entity.Blocker;


public abstract class Entity extends ShipPart{
    /**
     * 2d Vector holding the orientation and dimension of the entity
     */
    private Vector<Integer> orientation;
    
    
    /**
     * Position where the Startposition of the entity is placed.
     */
    private Point2D position;
    
    private List<Blocker> associatedBlockers;
    
    
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
    public Vector<Integer> getOrientation() {
        return orientation;
    }
    public void setOrientation(Vector<Integer> orientation) {
        this.orientation = orientation;
    }
    public Point2D getPosition() {
        return position;
    }
    public void setPosition(Point2D position) {
        this.position = position;
    }
    public List<Blocker> getAssociatedBlockers() {
        return associatedBlockers;
    }
    public void addBlocker(Blocker blocker) {
        getAssociatedBlockers().add(blocker);
    }
    
    
}
