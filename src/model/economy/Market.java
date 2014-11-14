package model.economy;

import java.util.ArrayList;

import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.Stock;

public class Market {
    
    private Stock stock;
    

    public Market() {
        this.stock = new Stock();
        this.initStock();
    }
    
    /**
     * initializing a stock for a market
     */
    private void initStock()
    {
        ArrayList<Material> materials = MaterialFactory.getInstance().getMaterials();
        ArrayList<Entity> entities = EntityFactory.getInstance().getEntities();
        
        this.stock.addMaterial(materials.get(0), 10);
        this.stock.addEntity(entities.get(0), 7);
    }
    
    public Stock getStock() {
        return this.stock;
    }
    
}
