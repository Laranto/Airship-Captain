package model.economy;

import java.util.ArrayList;

import model.GameState;
import model.factory.WareFactory;
import common.Constants;
import common.Utils;

public class Market {

    private Stock stock;

    public Market() {
        this.stock = new Stock();
        this.initStock();
    }
    
    /*
     * initializing the stock of this market randomly
     */
    private void initStock() {
        if(this.getStock().getWarelist().isEmpty())
        {
            ArrayList<Ware> wares = WareFactory.getInstance().getWares();
            try{
                StockItem stockItem = null;
                for (Ware ware: wares) {
                    stockItem = this.getStock().addTradeableWare( ware, Utils.getRandomIntBetween(10,  (int) Constants.WARE_STANDARD_AMOUNT * 2));
                    stockItem.getWare().setPrice(Economy.calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue()));
                }
                
                stockItem = this.getStock().addTradeableWare( wares.get(0), 200 );
                stockItem.getWare().setPrice(Economy.calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue()));
                
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * This method buys an item for an airship from a market
     * @param amount is the amount you want to buy
     * @param Ware is the ware you want to buy
     * @return returns the item itself the the bought was successful, otherwise it throws an exception
     * @throws Exception
     */
    public void buyItem(Ware ware, int amount) throws Exception {
        if(amount == 0){return;}
        
        StockItem marketItem = getStock().addTradeableWare(ware, -amount);
        StockItem airshipItem = GameState.getInstance().getAirship().getStock().addTradeableWare(ware, amount);
        
        Economy.calculateNewPriceFor(marketItem, airshipItem);
    }

    /**
     * This method sells an item from an airship to a market
     * @param ware the ware which is been traded
     * @param amount the amount of this ware
     * @throws Exception throws an exception if the resource which wants to be
     * traded doesn't exists or is not enough
     */
    public void sellItem(Ware ware, int amount) throws Exception {
        if(amount == 0){return;}
        
        StockItem airshipItem = GameState.getInstance().getAirship().getStock().addTradeableWare(ware, -amount);
        StockItem marketItem = getStock().addTradeableWare(ware, amount);
        
        Economy.calculateNewPriceFor(marketItem, airshipItem);
    }

    public Stock getStock() {
        return this.stock;
    }

}
