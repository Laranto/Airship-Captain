package model.economy;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.GameState;
import model.factory.WareFactory;
import model.gameobject.Airship;

import org.junit.Test;

public class MarketTest {

    @Test
    public void testStockList() {
        Market market = new Market();
        assertEquals(false, market.getStock().getWarelist().isEmpty());
    }
    

    //TODO: Test funktioniert nicht 2 von 5 Mal ist der neue Preis kleiner als der alte Preis!!!!!
    @Test
    public void testMarketPrice() throws Exception
    {
        Airship airship = new Airship();
        GameState.getInstance().setAirship(airship);
        Market market = new Market();
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);

        double oldPrice = ware.getPrice();
        market.buyItem(ware, 10);
        double neuerPreis = GameState.getInstance().getAirship().getStock().getStockItemByWareName(ware.getName()).getWare().getPrice();
        assertTrue("Der Marktpreis hat sich nicht verteuert alter Preis: "+oldPrice +"\n neuer Preis: "+neuerPreis, 
                    neuerPreis >= oldPrice);
       
    }
    
    @Test(expected = Exception.class)  
    public void testShipMarketTrade() throws Exception
    {
        Airship airship = new Airship();
        GameState.getInstance().setAirship(airship);
        Market market = new Market();
        
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);
        Ware ware2 = wares.get(2);
        
        
        assertTrue(airship.getStock().getWarelist().isEmpty());
        market.buyItem(ware, 2);
        assertFalse(airship.getStock().getWarelist().isEmpty());
        
        assertFalse(airship.getStock().getWarelist().size() > 1);
        market.buyItem(ware2, 10);
        assertEquals(2, airship.getStock().getWarelist().size());
        
        
        market.buyItem(ware, 5);
        market.sellItem(ware, 100);
    }
    
    

}
