package model.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.economy.Ware;

import org.junit.Test;

public class WareFactoryTest {

    @Test
    public void test() {
        try {
            WareFactory factory = (WareFactory) WareFactory.getInstance();

            ArrayList<Ware> wares = factory.getWares();

            assertTrue("Wares have been loaded", !wares.isEmpty());

            Ware ware = wares.get(0);
            assertNotNull(ware.getName());
            System.out.println(ware.getName());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
    }
}
