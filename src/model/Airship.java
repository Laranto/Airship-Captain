package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.LinkedList;

import model.factory.MaterialFactory;
import model.interfaces.Renderable;
import common.Constants;

public class Airship implements Renderable{
    private Material[][] shipBody;
    private Entity[][] equipment;
    private boolean isEmpty;
    
    public Airship() {
        shipBody    = new Material[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        equipment   = new Entity[Constants.AIRSHIP_WIDTH_TILES][Constants.AIRSHIP_HEIGHT_TILES];
        isEmpty=true;
    }
    
    
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
     * @return return ShipPart. If there is an equipment piece on the tile it will be returned. Else the material will be returned.
     * If nothing is there, null will be returned.
     */
    public ShipPart getShipPartByPosition(int tileX, int tileY)
    {
        if(this.equipment[tileX][tileY]!=null){
            return this.equipment[tileX][tileY];
        }
        return this.shipBody[tileX][tileY];
    }
    
    /*
    *   removes a material on the ship
    */
    public void removeMaterial(int tileX, int tileY)
    {
        shipBody[tileX][tileY] = null;
    }


    private boolean hasAdjacentTile(int tileX , int tileY) {
        return (tileX>0 && shipBody[tileX-1][tileY]!=null) 
                || (tileX<Constants.AIRSHIP_WIDTH_TILES-1 && shipBody[tileX+1][tileY]!=null) 
                || (tileY>0 && shipBody[tileX][tileY-1]!=null)
                || (tileY<Constants.AIRSHIP_HEIGHT_TILES-1 && shipBody[tileX][tileY+1]!=null);
    }
    
    


    @Override
    public void render(Graphics2D g) {
        AffineTransform originalCoordinates = g.getTransform();
        for(int x = 0;x<Constants.AIRSHIP_WIDTH_TILES;x++){
            for(int y = 0; y<Constants.AIRSHIP_HEIGHT_TILES;y++){
                //Move the g to the according position so the materials and entities must only render their image.
                g.setTransform(originalCoordinates);
                g.translate(x*Constants.TILE_SIZE, y*Constants.TILE_SIZE);
                if(shipBody[x][y]!=null){
                    shipBody[x][y].render(g);
                    if(equipment[x][y]!=null){
                        equipment[x][y].render(g);
                    }
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
    
}
