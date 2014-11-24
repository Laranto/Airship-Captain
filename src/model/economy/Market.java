package model.economy;

import java.util.ArrayList;
import java.util.List;

import model.factory.WareFactory;
import model.gameobject.Airship;

import common.Constants;
import common.Utils;

public class Market {

    private Stock stock;

    public Market() {
        this.stock = new Stock();
        this.initStock();
        this.initMarketPrice(stock.getWarelist());
    }
    
    /*
     * initializing the stock of this market randomly
     */
    private void initStock() {
        if(this.getStock().getWarelist().isEmpty())
        {
            ArrayList<Ware> wares = WareFactory.getInstance().getWares();
            try{
                for (int i = 0; i < wares.size(); i++) {
                    this.getStock().addTradeableWare( wares.get(i), Utils.getRandomIntBetween(10,  (int) Constants.WARE_STANDARD_AMOUNT * 2));
                }
                
                this.getStock().addTradeableWare( wares.get(0), 200 );
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * this method refreshes the new price of each item by looking at the market
     * state and the amount of the items
     */
    private void initMarketPrice(List<StockItem> stockItems) {
        for(StockItem stockItem: stockItems) {
            initMarketPrice(stockItem);
        }
    }

    private void initMarketPrice(StockItem stockItem) {
        stockItem.getWare().setPrice(calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue()));
    }

    /**
     * This method buys an item for an airship from a market
     * @param Airship the airship which wants to buy an item
     * @param Ware ist the ware you want to buy
     * @param amount is the amount you want to buy
     * @return returns the item itself the the bought was succesfull, otherwise it throws an exception
     * @throws Exception
     */
    public void buyItem(Airship airship, Ware ware, int amount) throws Exception {
        try{
            getStock().addTradeableWare(ware, -amount);
            airship.getStock().addTradeableWare(ware, amount);
            initMarketPrice(getStock().getStockItemByWareName(ware.getName()));
        }catch(Exception e){
            throw e;
        }
    }
    
    /**
     * This method sells an item from an airship to a market
     * @param airship the airship which wants to sell something
     * @param ware the ware which is been traden
     * @param amount the amount of this ware
     * @throws Exception throws an exception if the resource which wants to be
     * traden doesn't exists or is not enoug
     */
    public void sellItem(Airship airship, Ware ware, int amount) throws Exception {
        try{
            airship.getStock().addTradeableWare(ware, -amount);
            getStock().addTradeableWare(ware, amount);
            initMarketPrice(getStock().getStockItemByWareName(ware.getName()));
        }catch(Exception e){
            throw e;
        }
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
    
    


    public Stock getStock() {
        return this.stock;
    }

}
