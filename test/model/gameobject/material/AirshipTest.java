package model.gameobject.material;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.File;

import model.factory.MaterialFactory;
import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.entity.Weapon;
import model.gameobject.material.Floor;

import org.junit.Before;
import org.junit.Test;

import common.Constants;

public class AirshipTest {

    private Airship aship;
    private Material tFloor;
    private Material tWall;
    private Entity tEntity;
    private Entity tEntity2;
    
    @Before
    public void setUp() throws Exception {
        aship = new Airship();
        tFloor = new Floor("testF", 1, 1, 1, Constants.FOLDER_MATERIAL+"wood_planks"+File.separator+"img"+File.separator+"floor.png");
        tWall = new Wall("testW", 1, 1, 1, Constants.FOLDER_MATERIAL+"wood_planks"+File.separator+"img"+File.separator+"wall.png");
        tEntity = new Weapon("testWeapon", 1, 1, 1, Constants.CANNONBALL_IMAGE, Constants.ENTITY_ORIENTATION_RIGHT, new Dimension(2, 1), 5);
        tEntity2 = new Weapon("testWeapon2", 1, 1, 1, Constants.CANNONBALL_IMAGE, Constants.ENTITY_ORIENTATION_RIGHT, new Dimension(2, 1), 5);
    }

    
    @Test
    public void testFloorPlacementAllGood(){
       testAllGood(tFloor,Constants.AIRSHIP_WIDTH_TILES/2, Constants.AIRSHIP_HEIGHT_TILES/2);
    }
    @Test
    public void testWallPlacementAllGood(){
        testAllGood(tWall,Constants.AIRSHIP_WIDTH_TILES/2, Constants.AIRSHIP_HEIGHT_TILES/2);
    }
    @Test
    public void testPlacementAllGoodX0(){
        testAllGood(tWall,0, Constants.AIRSHIP_HEIGHT_TILES/2);
    }
    @Test
    public void testPlacementAllGoodY0(){
        testAllGood(tWall,Constants.AIRSHIP_WIDTH_TILES/2, 0);
    }
    @Test
    public void testPlacementAllGoodXmax(){
        testAllGood(tWall,Constants.AIRSHIP_WIDTH_TILES-1, Constants.AIRSHIP_HEIGHT_TILES/2);
    }
    @Test
    public void testPlacementAllGoodYmax(){
        testAllGood(tWall,Constants.AIRSHIP_WIDTH_TILES/2, Constants.AIRSHIP_HEIGHT_TILES-1);
    }
    
    private void testAllGood(Material mat, int x, int y){
        aship.placeMaterial(mat, x, y);
        validateAllGood(mat, x, y);
    }


    private void validateAllGood(Material mat , int x , int y) {
        Material testMaterial = mat; 
        Material placedMat = aship.getMaterialByPosition(x, y);
        assertNotNull(placedMat);
        assertEquals(testMaterial.getName(),placedMat.getName());
    }
    
    
    @Test
    public void outOfBoundsPlacementX0(){
        testNotPlaced(-1, 1, tFloor);
    }
    @Test
    public void outOfBoundsPlacementXM(){
        testNotPlaced(Constants.AIRSHIP_WIDTH_TILES, 1, tFloor);
    }
    @Test
    public void outOfBoundsPlacementY0(){
        testNotPlaced(1, -1, tFloor);
    }
    @Test
    public void outOfBoundsPlacementYM(){
        testNotPlaced(1, Constants.AIRSHIP_HEIGHT_TILES, tFloor);
    }
    
    private void testNotPlaced(int x, int y, Material mat){
        aship.placeMaterial(mat, x, y);
        assertTrue("is still Empty", aship.isEmpty());
    }
    
    @Test
    public void testNullMatPlacement(){
        testNotPlaced(1, 1, null);
        testAllGood(tFloor, 1, 1);
        aship.placeMaterial(null, 1, 1);
        validateAllGood(tFloor,1,1);
    }
    @Test
    public void testRemoveMaterial(){
        testAllGood(tFloor, 0, 0);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(0, 0);
        assertNull(aship.getMaterialByPosition(0, 0));
        assertTrue(aship.isEmpty());
    }
    @Test
    public void testRemoveMaterialWrongPlace(){
        testAllGood(tFloor, 0, 0);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(0, 1);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(1, 0);
        assertFalse(aship.isEmpty());
        assertNotNull(aship.getMaterialByPosition(0, 0));
        assertFalse(aship.isEmpty());
    }
    
    @Test
    public void testRemoveMaterialCorners(){
        testPlaceAndRemove(0,0);
        testPlaceAndRemove( Constants.AIRSHIP_WIDTH_TILES-1, 0);
        testPlaceAndRemove( 0, Constants.AIRSHIP_HEIGHT_TILES-1);
        testPlaceAndRemove( Constants.AIRSHIP_WIDTH_TILES-1, Constants.AIRSHIP_HEIGHT_TILES-1);
    }
    
    private void testPlaceAndRemove(int x, int y){
        testAllGood(tFloor, x, y);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(x, y);
        assertTrue(aship.isEmpty());
        assertNull(aship.getMaterialByPosition(x, y));
    }
    
    
    @Test
    public void testRemoveMaterialOutOfBounds(){
        testAllGood(tFloor, 0, 0);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(-1, 0);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(0, -1);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(Constants.AIRSHIP_WIDTH_TILES, 0);
        assertFalse(aship.isEmpty());
        aship.removeMaterial(0, Constants.AIRSHIP_HEIGHT_TILES);
        assertFalse(aship.isEmpty());
        assertNotNull(aship.getMaterialByPosition(0, 0));
        assertFalse(aship.isEmpty());
    }
    
    public void testName(){
        assertEquals(Constants.AIRSHIP_NAME_DEFAULT,aship.getName());
        String testName = "test";
        aship.setName(testName);
        assertEquals(testName, aship.getName());
        aship.setName(null);
        assertEquals(testName, aship.getName());
        aship.setName("");
        assertEquals(testName, aship.getName());
    }
    
    
    
    
    @Test
    public void testPlacement() {
        Material testMaterial = MaterialFactory.getInstance().getMaterials().get(0);
        aship.placeMaterial(testMaterial, 5, 5);
        assertNotNull(aship.getShipPartByPosition(5, 5));
        aship.placeMaterial(testMaterial, 5, 6);
        assertNotNull(aship.getShipPartByPosition(5, 6));
        aship.placeMaterial(testMaterial, 8, 8);
        assertNull(aship.getShipPartByPosition(8, 8));
    }
    
    @Test
    public void testJoined(){
        
        assertFalse(aship.isJoined());
        
        for(int i = 0;i<10;i++){
        	aship.placeMaterial(tFloor, 5, 5+i);	
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        for(int i = 0;i<10;i++){
        	aship.placeMaterial(tFloor, 6, 5+i);	
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        
        aship.removeMaterial(5, 7);
        aship.removeMaterial(6, 7);
        assertFalse("Airship is split", aship.isJoined());
    }
    @Test
    public void testJoinedY(){
        for(int i = 0;i<10;i++){
            aship.placeMaterial(tFloor, 5, 5+i);    
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        aship.removeMaterial(5, 7);
        assertFalse("Airship is split", aship.isJoined());
    }
    @Test
    public void testJoinedX(){
        for(int i = 0;i<10;i++){
            aship.placeMaterial(tFloor, 5+i, 5);    
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        aship.removeMaterial(7, 5);
        assertFalse("Airship is split", aship.isJoined());
    }
    
    
    private void buildPlaceEntityTestArea() {
        for(int i = 0;i<10;i++){
            aship.placeMaterial(tFloor, 5, 5+i);    
            aship.placeMaterial(tFloor, 6, 5+i);    
            aship.placeMaterial(tFloor, 7, 5+i);    
        }
    }
    
    private void compareEntities(Entity expected,Entity result){
        assertNotNull(result);
        assertEquals(expected.getName(), result.getName());
    }
    
    @Test
    public void testPlaceRemoveEntity(){
        buildPlaceEntityTestArea();
        aship.placeEntity(tEntity, 6, 5);
        compareEntities(tEntity, aship.getEntity(6, 5));
        compareEntities(tEntity, aship.getEntity(7, 5));
        assertNull(aship.getEntity(5, 5));
        aship.placeEntity(tEntity, 4, 5);
        assertNull(aship.getEntity(5, 5));
        assertNull(aship.getEntity(4, 5));
        tEntity2.rotate();
        aship.placeEntity(tEntity2, 6, 6);
        compareEntities(tEntity2, aship.getEntity(6, 6));
        compareEntities(tEntity2, aship.getEntity(5, 6));
        assertNull(aship.getEntity(7, 6));
    }

}
