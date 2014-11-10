package model.factory;

import static org.junit.Assert.*;

import java.util.ArrayList;
import model.Entity;

import model.Material;

import org.junit.Before;
import org.junit.Test;

public class EntityFactoryTest {

    private EntityFactory factory;
    
    @Before
    public void setUp() throws Exception {
        factory = EntityFactory.getInstance();
    }
    
    @Test
    public void testInstance()
    {
        assertTrue(factory != null);
    }

    @Test
    public void testEntities() {
        try {

            ArrayList<Entity> entities = factory.getEntities();

            assertTrue("Entities have been loaded", !entities.isEmpty());

            Entity object = entities.get(0);
            assertNotNull(object.getName());


        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
    }

}
