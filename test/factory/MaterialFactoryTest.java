package factory;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.Material;

import org.junit.Before;
import org.junit.Test;

import factory.MaterialFactory;


public class MaterialFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        try{
        MaterialFactory factory = new MaterialFactory();
        
        ArrayList<Material> materials = factory.getMaterials();
        
        assertTrue("Materials have been loaded", !materials.isEmpty());
        }catch(Exception e){
            e.printStackTrace();
            fail("Exception was thrown");
        }
    }

}
