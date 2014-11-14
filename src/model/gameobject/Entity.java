package model.gameobject;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.gameobject.entity.Blocker;


public abstract class Entity extends ShipPart{
    /**
     * 2d Vector holding the orientation and dimension of the entity
     */
    private Vector<Integer> orientation;
    
    private Dimension2D size;
    
    private List<Blocker> associatedBlockers;
    
    
    public Entity(String name , int value , int weight , int durability , BufferedImage image , Vector<Integer> orientation , Dimension2D size) {
        super(name , value , weight , durability , image);
        this.orientation = orientation;
        this.size = size;
        associatedBlockers=new ArrayList<>();
    }
    public Dimension2D getSize() {
        return size;
    }
    public void setSize(Dimension2D size) {
        this.size = size;
    }
    
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
    public List<Blocker> getAssociatedBlockers() {
        return associatedBlockers;
    }
    public void addBlocker(Blocker blocker) {
        getAssociatedBlockers().add(blocker);
    }
    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
    
    
    
    
    
}
