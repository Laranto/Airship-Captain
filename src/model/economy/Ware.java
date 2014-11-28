package model.economy;

import model.gameobject.GameObject;

public class Ware extends GameObject implements Tradeable{
    private static final long serialVersionUID = 1L;
    
    private double price;


    public Ware(String name , int value , int weight, double price) {
        super(name , value , weight);
        this.price = price;
    }

    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}
