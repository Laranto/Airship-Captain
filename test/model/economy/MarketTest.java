package model.economy;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.factory.WareFactory;
import model.gameobject.Airship;

import org.junit.Test;

public class MarketTest {

    @Test
    public void testStockList() {
        Market market = new Market();
        assertEquals(false, market.getStock().getWarelist().isEmpty());
    }
    

    
    @Test
    public void testMarketPrice() throws Exception
    {
        Airship airship = new Airship();
        Market market = new Market();
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);

        float oldPrice = ware.getPrice();
        market.buyItem(airship, ware, 10);
        assertTrue(ware.getPrice() > oldPrice);
       
    }
    
    @Test(expected = Exception.class)  
    public void testShipMarketTrade() throws Exception
    {
        Airship airship = new Airship();
        Market market = new Market();
        
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);
        Ware ware2 = wares.get(2);
        
        
        assertTrue(airship.getStock().getWarelist().isEmpty());
        market.buyItem(airship, ware, 2);
        assertFalse(airship.getStock().getWarelist().isEmpty());
        
        assertFalse(airship.getStock().getWarelist().size() > 1);
        market.buyItem(airship, ware2, 10);
        assertEquals(2, airship.getStock().getWarelist().size());
        
        
        market.buyItem(airship, ware, 5);
        airship.getStock().printStock();
        market.sellItem(airship, ware, 100);
    }
    
    

}
