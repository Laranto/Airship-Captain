package model.economy;

import java.io.Serializable;

import model.GameState;
import controller.WindowController;

public class Market implements Serializable{

    private Stock stock;

    public Market() {
        this.stock = new Stock();
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
        double totalCosts = ware.getPrice()*amount;
        Money captainMoney = GameState.getInstance().getAirship().getCaptain().getMoney();
        try{
            GameState.getInstance().getAirship().getCaptain().getMoney().removeAmount(ware.getPrice()*amount);
            StockItem marketItem = getStock().addTradeableWare(ware, -amount);
            StockItem airshipItem = GameState.getInstance().getAirship().getStock().addTradeableWare(ware, amount);
            
            Economy.calculateNewPriceFor(marketItem, airshipItem);
        }catch(ArithmeticException exp){
            WindowController.showError("Kein Geld", "Du hast nicht genug Geld, du brauchst : $ "+totalCosts+", aber hast nur: $ "+captainMoney.getAmount());
        }
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
        
        GameState.getInstance().getAirship().getCaptain().getMoney().addAmount(ware.getPrice()*amount);
        
        Economy.calculateNewPriceFor(marketItem, airshipItem);
    }

    public Stock getStock() {
        return this.stock;
    }

}
