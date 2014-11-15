package model.economy;

import java.util.ArrayList;

import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.factory.WareFactory;
import model.gameobject.Entity;
import model.gameobject.Material;

public class Market {
    
    private Stock stock;
    

    public Market() {
        this.stock = new Stock();
    }
    

    
    public Stock getStock() {
        return this.stock;
    }
    
}
