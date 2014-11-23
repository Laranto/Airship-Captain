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

/**
 * An abstract representation of a piece of equipment on the airship. Can be cannons, engines, tables or whatever. Just not hull parts.
 */
public abstract class Entity extends ShipPart {

    private static final long serialVersionUID = 1L;

    /**
     * 2d Vector holding the orientation of the entity
     */
    private Vector<Integer> orientation;

    /**
     * The actual size of the entity. This will be used when filling in blockers on placement. 
     */
    private Dimension2D     size;

    private List<Blocker>   associatedBlockers;

    /**
     * @param orientation Should have at least position 0 filled if you hope to rotate the entity without errors.
     */
    public Entity(String name , int value , int weight , int durability , BufferedImage image , Vector<Integer> orientation , Dimension2D size) {
        super(name , value , weight , durability , image);
        if(orientation!=null){
        this.orientation = new Vector<Integer>(orientation);
        }
        this.size = size;
        associatedBlockers = new ArrayList<>();
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    /**
     * Rotates the Entity from left to right and back.
     */
    public void rotate() {
        orientation.set(0, orientation.get(0) * -1);
        size.setSize(-1*size.getWidth(),size.getHeight());
    }

    public Vector<Integer> getOrientation() {
        return orientation;
    }

    /**
     * Forcefully sets an orientation to the Entity
     */
    public void setOrientation(Vector<Integer> targetOrientation) {
        if (Math.abs(targetOrientation.get(0)) + Math.abs(targetOrientation.get(1)) != 1) {
            throw new IllegalArgumentException("Must have a sensible orientation");
        }

        while (targetOrientation.get(0) != this.orientation.get(0)) {
            rotate();
        }
    }

    /**
     * @return the list of blockers representing the sprawl of this entity.
     */
    public List<Blocker> getAssociatedBlockers() {
        return associatedBlockers;
    }

    /**
     * @param blocker to be added as a part of the entity
     */
    public void addBlocker(Blocker blocker) {
        getAssociatedBlockers().add(blocker);
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        //Execute the rotation in the center of the root tile
        g.translate(Constants.TILE_SIZE/2, Constants.TILE_SIZE/2);
        //Rotate if the orientation demands it.
        g.rotate(Math.toRadians(Math.min(0, orientation.get(0)) * 180));
        //Awkwardly slide back in position
        g.translate(-Constants.TILE_SIZE/2, -Constants.TILE_SIZE/2);
        super.render(g);
        g.setTransform(originalCoordinates);
    }
}
