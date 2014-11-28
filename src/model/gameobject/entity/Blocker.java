package model.gameobject.entity;

import java.awt.Graphics2D;

import model.gameobject.Entity;

/**
 * This is a blocking class. Its used to mark a tile on the airship as used by a large entity
 */
public class Blocker extends Entity{
    private static final long serialVersionUID = 1L;
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
        //Debugbox, if needed
//        g.setColor(Color.PINK);
//        g.fillRect(0, 0, 5, 5);
    }

}
