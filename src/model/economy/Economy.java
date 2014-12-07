package model.economy;

import common.Constants;

public class Economy {
    
    /**
     * This method calculates the new price for the marketItem and the airshipItem
     * @param marketItem        The market item for witch the price is being calculated
     * @param airshipItem       The airship item which price is set by 
     *                          market item price * WARE_SELL_RATIO
     */
    public static void calculateNewPriceFor(StockItem marketItem, StockItem airshipItem) {
        double price = calculatePrice(marketItem.getAmount(), marketItem.getWare().getValue());
        marketItem.getWare().setPrice(price);
        airshipItem.getWare().setPrice(price*Constants.WARE_SELL_RATIO);
    }
    
    
    /**
     * This method calculates the new price of an item
     * @param currentAmount, is the current amount of an item
     * @param pricePerHundred, is the normal price for 100 pieces of this item (the method needs this informationf for calculating the price)
     * @return double the new price
     */
    public static double calculatePrice(int currentAmount, int pricePerHundred)
    {
        //TODO: regressive Berechnung des Preises.
        double newPrice = (pricePerHundred + pricePerHundred * (( ( (Constants.WARE_STANDARD_AMOUNT - currentAmount) / Constants.WARE_STANDARD_AMOUNT) ) ));
        if (newPrice < pricePerHundred / Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = pricePerHundred / Constants.WARE_MAX_INFLATION_FACTOR;
        } else if (newPrice > pricePerHundred * Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = pricePerHundred  * Constants.WARE_MAX_INFLATION_FACTOR;
        }
        return  Math.round(newPrice);
    }
}
