package model.economy;

import model.gameobject.GameObject;

public class Ware extends GameObject implements Tradeable{

    private float price;


    public Ware(String name , int value , int weight, float price) {
        super(name , value , weight);
        this.price = price;
    }

    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
