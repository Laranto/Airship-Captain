package factory;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Material;
import model.factory.MaterialFactory;

import org.junit.Before;
import org.junit.Test;

import sun.security.jca.GetInstance;


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
