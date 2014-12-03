package model.gameobject;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.economy.Stock;
import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.gameobject.entity.Blocker;
import model.gameobject.material.Floor;

import common.Constants;

public class Airship extends GameObject implements Renderable{
    private static final long serialVersionUID = 1L;
    private Material[][] shipBody;
    private Entity[][] equipment;
    private boolean isEmpty;
    private Captain captain;
    private Stock stock;
    private int speed;
    private boolean isRotated;
    
    public Airship() {
        super(null,0,0);
        shipBody    = new Material[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        equipment   = new Entity[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        isEmpty=true;
        captain = new Captain();
        this.stock = new Stock();
        this.setName(Constants.AIRSHIP_NAME_DEFAULT);
        isRotated=false;
        //TODO has to be calculated (e.x. the amount of engines the ship has)
        this.speed = 22;
    }
    
    /**
     * Returns the Captain of the airship
     * @return  Captain
     */
    public Captain getCaptain() {
        return captain;
    }
    
    
    /**
     * Places a new instance of the given material at the given location. Only possible if the location is empty space.
     */
    public boolean placeMaterial(Material mat, int tileX, int tileY){
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
       //Nothing on that tile yet
        if(inBounds(tileX, tileY) && shipBody[tileX][tileY] == null && mat!=null){
            if(isEmpty || hasAdjacentTile(tileX,tileY)){
                shipBody[tileX][tileY] = (Material) MaterialFactory.getInstance().instanzise(mat);
                isEmpty=false;
                return true;
            }
        }
        return false;
    }
    
    
    private boolean inBounds(int tileX , int tileY) {
        return tileX>=0&&tileY>=0&&tileX<Constants.AIRSHIP_WIDTH_TILES&&tileY<Constants.AIRSHIP_HEIGHT_TILES;
    }

    /**
     * Places an entity at the given position if possible. Checks the Airship body and other placed entities if there is space to place it.
     * @param entity Entity in the state, in which it should be placed 

     */
    public boolean placeEntity(Entity entity, int tileX, int tileY){
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        if(entity!=null && inBounds(tileX, tileY)){
            List<Point> checkedPoints = new LinkedList<>();
            if(canPlaceEntity(entity,tileX,tileY,checkedPoints)){
                Entity placedEntity = (Entity) EntityFactory.getInstance().instanzise(entity);
                placeEntityOnGrid(tileX, tileY, placedEntity);
                for (Point point : checkedPoints) {
                    if(equipment[point.x][point.y]==null){
                        Blocker blocker = new Blocker(placedEntity);
                        placedEntity.addBlocker(blocker);
                        placeEntityOnGrid(point.x, point.y,blocker);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void placeEntityOnGrid(int tileX , int tileY , Entity entity) {
        if(inBounds(tileX, tileY)){
        equipment[tileX][tileY] = entity;
        entity.setPosition(tileX, tileY);
        }
    }


    
    
    /**
     * Checks if an entity can be placed at the given point judging on placed entities and shipBody.  
     * @param checkedPoints an empty list that will be filled with points that have been checked. 
     * Only completely filled when the object can be placed.
     * @return true when there is sufficient space to place the entity with the given orientation
     */
    private boolean canPlaceEntity(Entity entity , int tileX , int tileY, List<Point> checkedPoints) {
        
        //Get the entity sizes
        int xExtend = (int) entity.getSize().getWidth()  
                , yExtend = (int) entity.getSize().getHeight();
        
        //Account for the selected root. for that cut off 1 in the direction in which it is facing
        int xNormExtend = xExtend - xExtend/Math.abs(xExtend);
        int yNormExtend = yExtend - yExtend/Math.abs(yExtend);
        
        //Get the lower as start and the higher as end point.
        int fromX=Math.min(tileX,tileX+xNormExtend)
                ,toX=Math.max(tileX,tileX+xNormExtend);
        int fromY=Math.min(tileY,tileY+yNormExtend)
                ,toY=Math.max(tileY,tileY+yNormExtend);
        
        for(int x = fromX;x<=toX;x++){
            for(int y = fromY;y<=toY;y++){
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
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        
        if(inBounds(tileX, tileY)){
            if(this.equipment[tileX][tileY]!=null){
                return this.equipment[tileX][tileY];
            }
            return this.shipBody[tileX][tileY];
        }
        return null;
    }
    
    public Material getMaterialByPosition(int tileX, int tileY){
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        if(inBounds(tileX, tileY)){
        return this.shipBody[tileX][tileY];
        }
        return null;
    }
    
    /**
    *   Removes a material from the ship. There may not be any entities (or blockers) in the way for this 
    */
    public Material removeMaterial(int tileX, int tileY)
    {
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        /**
         * checking if the remove method has been called on the right
         */
        if(inBounds(tileX, tileY)&& equipment[tileX][tileY]==null)
        {
            Material tempMaterial = shipBody[tileX][tileY];
        	shipBody[tileX][tileY] = null;
        	updateIsEmpty();
        	
        	return tempMaterial;
        }
        return null;
    }

    private void updateIsEmpty() {
        for(int x = 0;x<Constants.AIRSHIP_WIDTH_TILES;x++){
            for(int y = 0;y<Constants.AIRSHIP_HEIGHT_TILES;y++){
                if(shipBody[x][y]!=null){
                    isEmpty=false;
                    return;
                }
            }
        }
        isEmpty=true;
    }

    /**
     * Removes the entity that lies on position tileX, tileY. If it's a blocker the parent entity will be removed.<br>
     * All blockers associated with the entity will be removed as well.
     * @return the Entity which was removed
     */
    public Entity removeEntity(int tileX , int tileY) {
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        if(inBounds(tileX, tileY)){
            Entity selected = getEntity(tileX, tileY);
            if(selected == null){
                return null;
            }
            for (Blocker blocker : selected.getAssociatedBlockers()) {
                removeEntity(blocker);
            }
            removeEntity(selected);
            return selected;
        }
        return null;
    }
    
    /**
     * Removes a entity using its position
     */
    private void removeEntity(Entity entity){
        Point pos = entity.getPosition();
        equipment[pos.x][pos.y]=null;
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
    
    

    
    /**
     * Causes the ship to be roated. This also concerns all inputs
     */
    public void rotate(){
        isRotated= (!isRotated);
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        if(isRotated){
            g.translate(Constants.AIRSHIP_WIDTH_TILES*Constants.TILE_SIZE, (Constants.AIRSHIP_HEIGHT_TILES-1)*Constants.TILE_SIZE);
            g.rotate(Math.toRadians(180));
        }
        renderMaterial(g);
        renderEquipment(g);
        g.setTransform(originalCoordinates);
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
     * @return true, if the airship is in one piece, false when there is no airship or the ship is split in one or more pieces
     */
    public boolean isJoined(){
        if(isEmpty){
            return false;
        }
    	Material[][] checkBody = new Material[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
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
		for(int x=0;x<Constants.AIRSHIP_WIDTH_TILES ;x++){
		    for(int y=0; y<Constants.AIRSHIP_HEIGHT_TILES; y++){
               	 if(shipBody[x][y]!=null){
               		 checkBody[x][y]=shipBody[x][y];
               		 uncheckedPositions.push(new Point(x, y));
               		 return;
               	 }
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
        if(isRotated){
            tileX = rotateSelectedX(tileX);
            tileY = rotateSelectedY(tileY);
        }
        if(inBounds(tileX, tileY)){
            Entity selected =equipment[tileX][tileY];
            if(selected instanceof Blocker){
                selected = ((Blocker)selected).getReferencedEntity();
            }
            return selected;
        }
        return null;
    }


    @Override
    public String getName() {
        return super.getName();
    }


    @Override
    public void setName(String name) {
        if(name!=null && !"".equals(name)){
            super.setName(name);
        }
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
    
    
    private int rotateSelectedY(int tileY) {
        return Constants.AIRSHIP_HEIGHT_TILES-2-tileY;
    }

    private int rotateSelectedX(int tileX) {
        return Constants.AIRSHIP_WIDTH_TILES-1-tileX;
    }


    @Override
    public void setWeight(int weight) {
        throw new NoSuchMethodError();
    }
    
    public Stock getStock()
    {
        return this.stock;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void resetRotation() {
        if(isRotated){
            rotate();
        }
    }


}
