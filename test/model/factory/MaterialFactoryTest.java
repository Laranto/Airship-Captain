package model.factory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.factory.MaterialFactory;
import model.gameobject.Material;

import org.junit.Before;
import org.junit.Test;


public class MaterialFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        try{
        MaterialFactory factory = (MaterialFactory) MaterialFactory.getInstance();
        
        ArrayList<Material> materials = factory.getMaterials();
        
        assertTrue("Materials have been loaded", !materials.isEmpty());
        
        Material mat = materials.get(0);
        assertNotNull(mat.getName());
        assertNotNull(mat.getImage());
        
        }catch(Exception e){
            e.printStackTrace();
            fail("Exception was thrown");
        }
    }

}
