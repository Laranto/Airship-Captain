package model.material;

import static org.junit.Assert.*;
import model.factory.MaterialFactory;
import model.gameobject.Airship;
import model.gameobject.Material;

import org.junit.Before;
import org.junit.Test;

public class AirshipTest {

    private Airship aship;
    
    @Before
    public void setUp() throws Exception {
        aship = new Airship();
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
    public void testJunction(){
    	Material testMaterial = MaterialFactory.getInstance().getMaterials().get(0);
        for(int i = 0;i<10;i++){
        	aship.placeMaterial(testMaterial, 5, 5+i);	
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        for(int i = 0;i<10;i++){
        	aship.placeMaterial(testMaterial, 6, 5+i);	
        }
        assertTrue("Airship is in one piece", aship.isJoined());
        
        aship.removeMaterial(5, 7);
        aship.removeMaterial(6, 7);
        assertFalse("Airship is split", aship.isJoined());
    }

}
