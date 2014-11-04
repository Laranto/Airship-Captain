package model.material;

import static org.junit.Assert.*;
import model.Airship;
import model.Material;
import model.factory.MaterialFactory;

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

}
