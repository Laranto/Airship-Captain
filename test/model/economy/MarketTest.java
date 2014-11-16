package model.economy;

import java.util.ArrayList;

import junit.framework.Assert;
import model.factory.WareFactory;

import org.junit.Test;

public class MarketTest {




    @Test
    public void testStockList() {
        Market market = new Market();
        Assert.assertEquals(false, market.getStock().getWarelist().isEmpty());
    }
    

    
    @Test
    public void testMarketPrice()
    {
        Market market = new Market();
        ArrayList<Ware> wares = WareFactory.getInstance().getWares();
        Ware ware = wares.get(1);
        
        try {
            float oldPrice = ware.getPrice();
            System.out.println(oldPrice);
            Assert.assertTrue(ware.getPrice() > oldPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        market.printMarketItems();
       
    }

}
