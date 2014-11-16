package model.economy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import model.factory.WareFactory;

import common.Constants;
import common.Utils;

public class Market {

    private Stock stock;

    public Market() {
        this.stock = new Stock();
        this.initStock();
        this.initMarketPrice();
    }

    /*
     * initializing the stock of this market randomly
     */
    private void initStock() {
        if(this.getStock().getWarelist().isEmpty())
        {
            ArrayList<Ware> wares = WareFactory.getInstance().getWares();
    
            for (int i = 0; i < wares.size(); i++) {
                this.getStock().addTradeableWare( wares.get(i), Utils.getRandomIntBetween(10,  (int) Constants.WARE_STANDARD_AMOUNT * 2));
            }
            
            this.getStock().addTradeableWare( wares.get(0), 200 );
        }
    }

    /**
     * this method refreshes the new price of each item by looking at the market
     * state and the amount of the items
     */
    private void initMarketPrice() {

        Iterator<Entry<Ware, Integer>> it = this.getStock().getWarelist().entrySet().iterator();
        while (it.hasNext()) {
            Entry<Ware, Integer> pairs = it.next();

            Ware ware = (Ware) pairs.getKey();
            Integer amount = (Integer) pairs.getValue();
            ware.setPrice( this.calculatePrice(amount, ware.getValue() ) );
        }
    }

    /**
     * @param Ware ist the ware you want to buy
     * @param amount is the amount you want to buy
     * @return returns the item itself the the bought was succesfull, otherwise it throws an exception
     * @throws Exception
     */
    public Ware buyItem(Ware ware, int amount) throws Exception {
        if (this.getStock().getWarelist().containsKey(ware)) {
            int value = this.getStock().getWarelist().get(ware);
            
            if(value - amount < 0)
            {
                throw new Exception("There are only : "+(int) value+" items left.");
            }else{
                this.getStock().getWarelist().put(ware, value - amount);
                initMarketPrice();
            }
        }else{
            throw new Exception("Resource doesn't exist.");
        }

        return null;
    }

    
    /**
     * This method calculates the new price of an item
     * @param amountLeft, is the current amount of an item
     * @param defaultPrice, is the normal price for 100 pieces of this item (the method needs this informationf for calculating the price)
     * @return float the new price
     */
    private float calculatePrice(int amountLeft, int defaultPrice)
    {

        float newPrice = (defaultPrice + defaultPrice * (( ( (Constants.WARE_STANDARD_AMOUNT - amountLeft) / Constants.WARE_STANDARD_AMOUNT) ) ));

        if (newPrice < defaultPrice / Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = defaultPrice / Constants.WARE_MAX_INFLATION_FACTOR;
        } else if (newPrice > defaultPrice * Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = defaultPrice  * Constants.WARE_MAX_INFLATION_FACTOR;
        }
        
        return  Math.round(newPrice);
    }
    
    
    /**
     * Printing the state of the market in the console
     */
    public void printMarketItems()
    {
        System.out.println("Name\t\t\t\tReferenz\t\t\t\t\t\t\t\tAnzahl\t\t\tPreis");
        
        Iterator<Entry<Ware, Integer>> it = this.getStock().getWarelist().entrySet().iterator();
        while (it.hasNext()) {
            Entry<Ware, Integer> pairs = it.next();
            Ware ware = pairs.getKey();
            Integer amount = pairs.getValue();
            System.out.println(ware.getName()+"\t"+ware +"\t"+amount+"\t\t\t"+ware.getPrice());
        }
    }

    public Stock getStock() {
        return this.stock;
    }

}
