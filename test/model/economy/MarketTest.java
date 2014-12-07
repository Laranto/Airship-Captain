package model.economy;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import model.GameState;
import model.economy.Market;
import model.economy.Ware;
import model.factory.WareFactory;
import model.gameobject.Airship;
import model.navigation.Harbor;

import org.junit.Test;

public class MarketTest {

    @Test
    public void testStockList() {
        Market market = new Market();
        assertEquals(false, market.getStock().getWarelist().isEmpty());
    }
    

    @Test
    /**
     * Testing whether the price is rising when I buy a lot of an item
     * @throws Exception throws an Exception when I can't buy something
     */
    public void testMarketPrice() throws Exception
    {
        Airship airship = new Airship();
        GameState.getInstance().setAirship(airship);
        GameState.getInstance().setCurrentHarbor(new Harbor(new Market(), new Point(310, 340), true));
        Market market = GameState.getInstance().getCurrentHarbor().getMarket();

        String wareName = "Kalant Cabernet Sauvignon 2012";
        Ware ware = market.getStock().getStockItemByWareName(wareName).getWare();
        double oldPrice = ware.getPrice();
        market.buyItem(ware, market.getStock().getStockItemByWareName(wareName).getAmount() /2);
        double neuerPreis = market.getStock().getStockItemByWareName(wareName).getWare().getPrice();
        
        assertTrue("Der Marktpreis hat sich nicht verteuert alter Preis: "+oldPrice +"\n neuer Preis: "+neuerPreis,  neuerPreis >= oldPrice);
    }
    
    /**
     * Tests if the transfer between the market and ship is been successful
     * @throws Exception
     */
    @Test
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

    }
    
    @Test(expected = Exception.class)  
    public void testOverSellingBuyingItems()  throws Exception
    {
        Airship airship = new Airship();
        GameState.getInstance().setAirship(airship);
        Market market = new Market();
        
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);
        
        market.buyItem(ware, 5);
        market.sellItem(ware, 100);
    }
    

}
