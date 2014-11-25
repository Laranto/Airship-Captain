package model.economy;

import java.util.ArrayList;

import model.factory.WareFactory;
import model.gameobject.Airship;

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
     * This method buys an item for an airship from a market
     * @param Airship the airship which wants to buy an item
     * @param Ware ist the ware you want to buy
     * @param amount is the amount you want to buy
     * @return returns the item itself the the bought was succesfull, otherwise it throws an exception
     * @throws Exception
     */
    public void buyItem(Airship airship, Ware ware, int amount) throws Exception {
        getStock().addTradeableWare(ware, -amount);
        airship.getStock().addTradeableWare(ware, amount);
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
        airship.getStock().addTradeableWare(ware, -amount);
        getStock().addTradeableWare(ware, amount);
    }

    public Stock getStock() {
        return this.stock;
    }

}
