package model.gameobject;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.gameobject.entity.Blocker;
import model.gameobject.material.Floor;
import common.Constants;

public class Airship extends GameObject implements Renderable{
    private Material[][] shipBody;
    private Entity[][] equipment;
    private boolean isEmpty;
    
    public Airship() {
        super(null,0,0);
        shipBody    = new Material[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        equipment   = new Entity[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        isEmpty=true;
    }
    
    
    /**
     * Places a new instance of the given material at the given location. Only possible if the location is empty space.
     */
    public void placeMaterial(Material mat, int tileX, int tileY){
        
        //Nothing on that tile yet
        if(shipBody[tileX][tileY] == null && mat!=null){
            if(isEmpty || hasAdjacentTile(tileX,tileY)){
                shipBody[tileX][tileY] = (Material) MaterialFactory.getInstance().instanzise(mat);
                isEmpty=false;
            }
        }
    }
    
    
    /**
     * Places an entity at the given position if possible. Checks the Airship body and other placed entities if there is space to place it.
     * @param entity Entity in the state, in which it should be placed 

     */
    public boolean placeEntity(Entity entity, int tileX, int tileY){
        if(entity!=null){
            List<Point> checkedPoints = new LinkedList<>();
            if(canPlaceEntity(entity,tileX,tileY,checkedPoints)){
                Entity placedEntity = (Entity) EntityFactory.getInstance().instanzise(entity);
                equipment[tileX][tileY] = placedEntity;
                for (Point point : checkedPoints) {
                    if(equipment[point.x][point.y]==null){
                        Blocker blocker = new Blocker(placedEntity);
                        placedEntity.addBlocker(blocker);
                        equipment[point.x][point.y]=blocker;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if an entity can be placed at the given point judging on placed entities and shipBody.  
     * @param checkedPoints an empty list that will be filled with points that have been checked. 
     * Only completely filled when the object can be placed.
     * @return true when there is sufficient space to place the entity with the given orientation
     */
    private boolean canPlaceEntity(Entity entity , int tileX , int tileY, List<Point> checkedPoints) {
        int xExtend = (int) entity.getSize().getWidth()
                , yExtend = (int) entity.getSize().getHeight();
        int fromX=Math.min(tileX,tileX+xExtend)
                ,toX=Math.max(tileX,tileX+xExtend);
        int fromY=Math.min(tileY,tileY+yExtend)
                ,toY=Math.max(tileY,tileY+yExtend);
        for(int x = fromX;x<toX;x++){
            for(int y = fromY;y<toY;y++){
                if(shipBody[x][y] instanceof Floor && equipment[x][y]==null){
                    checkedPoints.add(new Point(x, y));
                }
            }
        }
        
        return checkedPoints.size()==Math.abs(xExtend*yExtend);
    }


    /**
     * @return return ShipPart. If there is an equipment piece on the tile it will be returned. Else the material will be returned.
     * If nothing is there, null will be returned.<br>
     * It is possible that a blocker is returned.
     */
    public ShipPart getShipPartByPosition(int tileX, int tileY)
    {
        if(this.equipment[tileX][tileY]!=null){
            return this.equipment[tileX][tileY];
        }
        return this.shipBody[tileX][tileY];
    }
    
    /*
    *   Removes a material from the ship. There may not be any entities (or blockers) in the way for this 
    */
    public boolean removeMaterial(int tileX, int tileY)
    {
        /**
         * checking if the remove method has been called on the right
         */
        if(tileX >= 0 && tileX < Constants.AIRSHIP_WIDTH_TILES 
                && tileY >= 0 && tileY < Constants.AIRSHIP_WIDTH_TILES
                && equipment[tileX][tileY]==null)
        {
        	shipBody[tileX][tileY] = null;
        	return true;
        }
        return false;
    }


    /**
     * Checks if the tile at the given position has other Material tiles next to it
     */
    private boolean hasAdjacentTile(int tileX , int tileY) {
        return (tileX>0 && shipBody[tileX-1][tileY]!=null) 
                || (tileX<Constants.AIRSHIP_WIDTH_TILES-1 && shipBody[tileX+1][tileY]!=null) 
                || (tileY>0 && shipBody[tileX][tileY-1]!=null)
                || (tileY<Constants.AIRSHIP_HEIGHT_TILES-1 && shipBody[tileX][tileY+1]!=null);
    }
    
    


    @Override
    public void render(Graphics2D g) {
        renderMaterial(g);
        renderEquipment(g);
    }


    public void renderEquipment(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        for(int x = 0;x<Constants.AIRSHIP_WIDTH_TILES;x++){
            for(int y = 0; y<Constants.AIRSHIP_HEIGHT_TILES;y++){
                //Move the g to the according position so the materials and entities must only render their image.
                g.setTransform(originalCoordinates);
                g.translate(x*Constants.TILE_SIZE, y*Constants.TILE_SIZE);
                if(equipment[x][y]!=null){
                    equipment[x][y].render(g);
                }
            }
        }
        g.setTransform(originalCoordinates);
    }


    public void renderMaterial(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        for(int x = 0;x<Constants.AIRSHIP_WIDTH_TILES;x++){
            for(int y = 0; y<Constants.AIRSHIP_HEIGHT_TILES;y++){
                //Move the g to the according position so the materials and entities must only render their image.
                g.setTransform(originalCoordinates);
                g.translate(x*Constants.TILE_SIZE, y*Constants.TILE_SIZE);
                if(shipBody[x][y]!=null){
                    shipBody[x][y].render(g);
                }else{
                    renderEmptySpace(g);
                }
            }
        }
        g.setTransform(originalCoordinates);
    }
    
    /**
     * Calculates if the airship is in one piece of if it's split in parts with open space in between.
     * <br>Use with caution, might take a bit when checking large ships
     * @return true, if the airship is in one piece.
     */
    public boolean isJoined(){
    	Material[][] checkBody = new Material[shipBody.length][shipBody[0].length];
    	LinkedList<Point> uncheckedPositions = new LinkedList<>();
    	findStartpoint(checkBody, uncheckedPositions);
    	while(!uncheckedPositions.isEmpty()){
    		checkFirstPosition(checkBody, uncheckedPositions);
    	}
    	return Arrays.deepEquals(shipBody, checkBody);
    }


	private void checkFirstPosition(Material[][] checkBody,
			LinkedList<Point> uncheckedPositions) {
		Point pos = uncheckedPositions.pollFirst();
		for(int x = -1;x<=1;x++){
			for(int y = -1;y<=1;y++){
				if(Math.abs(x)+Math.abs(y)==1){
					int newX = pos.x+x;
					int newY = pos.y+y;
					if(newX>=0 && newX<Constants.AIRSHIP_WIDTH_TILES 
						&& newY>=0 && newY<Constants.AIRSHIP_HEIGHT_TILES 
						&& shipBody[newX][newY]!=null 
						&& checkBody[newX][newY]==null){
						checkBody[newX][newY]=shipBody[newX][newY];
						uncheckedPositions.push(new Point(newX,newY));
					}
				}
			}
		}
	}


	private void findStartpoint(Material[][] checkBody,
			LinkedList<Point> uncheckedPositions) {
		for(int x=0, y=0;x<Constants.AIRSHIP_WIDTH_TILES && y<Constants.AIRSHIP_HEIGHT_TILES;x++, y++){
           	 if(shipBody[x][y]!=null){
           		 checkBody[x][y]=shipBody[x][y];
           		 uncheckedPositions.push(new Point(x, y));
           		 break;
           	 }
          }
	}


    /**
     * Some dummy method to render empty space. Should be replaced with some nice graphical gimmiks that we have clouds and such.
     * @param g Graphics
     */
    private void renderEmptySpace(Graphics2D g) {
        g.setColor(Constants.COLOR_SKYBLUE);
        g.fillRect(0, 0, Constants.TILE_SIZE,Constants.TILE_SIZE);
    }


    /**
     * Get an entity at the given position if possible.
     * @param tileX     x-Coordinate to be checked for entity
     * @param tileY     y-Coordinate to be checked for entity
     * @return entity   If an entity has been found or <code>null</code> if no entity has been found.
     *                  
     */
    public Entity getEntity(int tileX, int tileY) {
        return equipment[tileX][tileY];
    }


    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }


    @Override
    public int getValue() {
        int value = 0;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y<=1;y++){
                if(shipBody[x][y]!=null){
                    value+=shipBody[x][y].getValue();
                }
                if(equipment[x][y]!=null){
                    value+=equipment[x][y].getValue();
                }
            }
         }
        return value;
    }


    @Override
    public void setValue(int value) {
        throw new NoSuchMethodError();
    }


    @Override
    public int getWeight() {
        int weight = 0;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y<=1;y++){
                if(shipBody[x][y]!=null){
                    weight+=shipBody[x][y].getWeight();
                }
                if(equipment[x][y]!=null){
                    weight+=equipment[x][y].getWeight();
                }
            }
         }
        return weight;
    }


    @Override
    public void setWeight(int weight) {
        throw new NoSuchMethodError();
    }
    
    
    
    
}
