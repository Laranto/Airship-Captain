package model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import view.Renderable;

import common.Constants;

import factory.MaterialFactory;

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
        if(shipBody[tileX][tileY] == null){
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
     * Some dummy method to render empty space. Should be replaced with some nice graphical gimmiks that we have clouds and such.
     * @param g Graphics
     */
    private void renderEmptySpace(Graphics2D g) {
        g.setColor(Constants.COLOR_SKYBLUE);
        g.fillRect(0, 0, Constants.TILE_SIZE,Constants.TILE_SIZE);
    }
    
}
