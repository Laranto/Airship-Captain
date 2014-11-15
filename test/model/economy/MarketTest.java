package model.economy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import model.factory.WareFactory;
import model.gameobject.material.Wall;

import org.junit.Before;
import org.junit.Test;

public class MarketTest {

    private Market market;

    @Before
    public void init() {
        market = new Market();
    }

    @Test
    public void testStockList() {
        /**
         * this market contains all ware and 10 of each item
         */
        
        ArrayList<Tradeable> ware = WareFactory.getInstance().getWare();
        
        for(Tradeable tradeableWare : ware)
        {
            market.getStock().addTradeableWare(tradeableWare, 10);
        }
        

        /**
         * printing the list of items in this stock (just for testing purpose)
         */
        Iterator it = market.getStock().getWarelist().entrySet().iterator();
        System.out.println(market.getStock().getWarelist().size());
        
        
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            
            
            Tradeable material = (Tradeable) pairs.getKey();
            Integer amount = (Integer) pairs.getValue();

            System.out.println(pairs.getKey() instanceof Wall);

            System.out.println(material + " " + amount);
            it.remove();
        }

    }

}
