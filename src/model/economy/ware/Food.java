package model.economy.ware;

import model.economy.Ware;

/**
 * Every food object from the xml file should be an object of 
 * this food class
 */
public class Food extends Ware {
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param String the name of the food
     * @param int the value of this food
     * @param int the weight of the food
     * @param double the price of the food
     */
    public Food(String name, int value, int weight, double price) {
        super(name, value, weight, price);
    }
}
