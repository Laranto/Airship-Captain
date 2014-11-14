package model.gameobject.entity;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import model.gameobject.Entity;

/**
 * This is a blocking class. Its used to mark a tile on the airship as used by a large entity
 */
public class Blocker extends Entity{

    private Entity referencedEntity;
    


    public Blocker(Entity referencedEntity) {
        super(null,0,0,0,null,null,null);
        this.referencedEntity = referencedEntity;
    }


    public Entity getReferencedEntity() {
        return referencedEntity;
    }


    @Override
    public void render(Graphics2D g) {
    }

}
