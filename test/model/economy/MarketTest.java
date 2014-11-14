package model.economy;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {
    
    private Market market;
    
    @Before
    public void init()
    {
        market = new Market();
    }
    
    
    @Test
    public void testStockList()
    {
        market.getStock().listStock();
        
    }



}
