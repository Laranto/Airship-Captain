package model.economy;

import common.Constants;

public class Economy {
    /**
     * This method calculates the new price of an item
     * @param amountLeft, is the current amount of an item
     * @param defaultPrice, is the normal price for 100 pieces of this item (the method needs this informationf for calculating the price)
     * @return float the new price
     */
    public static float calculatePrice(int amountLeft, int defaultPrice)
    {

        float newPrice = (defaultPrice + defaultPrice * (( ( (Constants.WARE_STANDARD_AMOUNT - amountLeft) / Constants.WARE_STANDARD_AMOUNT) ) ));

        if (newPrice < defaultPrice / Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = defaultPrice / Constants.WARE_MAX_INFLATION_FACTOR;
        } else if (newPrice > defaultPrice * Constants.WARE_MAX_INFLATION_FACTOR) {
            newPrice = defaultPrice  * Constants.WARE_MAX_INFLATION_FACTOR;
        }
        
        return  Math.round(newPrice);
    }
}
