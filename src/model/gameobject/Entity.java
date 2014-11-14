package model.gameobject;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import common.Constants;

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
        Vector<Integer> rotatedOrientation = new Vector<>(2);
        rotatedOrientation.add(orientation.get(1));
        rotatedOrientation.add(orientation.get(0)-1);
        this.orientation = rotatedOrientation;
        size.setSize(size.getHeight(), size.getWidth()*-1);
    }
    public void rotateRight(){
        Vector<Integer> rotatedOrientation = new Vector<>(2);
        rotatedOrientation.add(orientation.get(1)*-1);
        rotatedOrientation.add(orientation.get(0));
        this.orientation = rotatedOrientation;
        size.setSize(size.getHeight()*-1, size.getWidth());
    }
    public Vector<Integer> getOrientation() {
        return orientation;
    }
    public void setOrientation(Vector<Integer> targetOrientation) {
        if(Math.abs(targetOrientation.get(0))+Math.abs(targetOrientation.get(1))!=1){
            throw new IllegalArgumentException("Must have a sensible orientation");
        }
        
        while(targetOrientation.get(0)!=this.orientation.get(0)
                ||targetOrientation.get(1)!=this.orientation.get(1)){
            rotateRight();
        }
    }
    public List<Blocker> getAssociatedBlockers() {
        return associatedBlockers;
    }
    public void addBlocker(Blocker blocker) {
        getAssociatedBlockers().add(blocker);
    }
    @Override
    public void render(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        if(orientation.get(0)==-1){
            g.rotate(Math.toRadians(180));
            //Compensate for rotation
            g.translate(-1*Constants.TILE_SIZE, -1*Constants.TILE_SIZE);
        }else if(orientation.get(0)==0){
            g.rotate(Math.toRadians(90*orientation.get(1)));
            if(orientation.get(1)<0){
            g.translate(-1*Constants.TILE_SIZE,0);
            }else{
                g.translate(0,-1*Constants.TILE_SIZE);
            }
            
        }
        super.render(g);
        g.setTransform(originalCoordinates);
    }
}
