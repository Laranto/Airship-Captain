package model.factory;

import java.util.ArrayList;

import model.economy.Tradeable;
import model.gameobject.Entity;
import model.gameobject.Material;

public class WareFactory {

    private static WareFactory instance;
    private ArrayList<Tradeable> ware;
    
    public WareFactory() {
        ware = new ArrayList<Tradeable>();
    }
    
    public ArrayList<Tradeable> getWare() {
        if(!ware.isEmpty())
        {
            return this.ware;
        }
        
        ArrayList<Material> materials = MaterialFactory.getInstance().getMaterials();
        
        for(Material material : materials)
        {
            if(material instanceof Tradeable)
            {
                ware.add( (Tradeable) material);
            }
        }
        
        ArrayList<Entity> entities = EntityFactory.getInstance().getEntities();
        
        for(Entity entity : entities)
        {
            if(entity instanceof Tradeable)
            {
                ware.add( (Tradeable) entity);
            }
        }
        
        
        return this.ware;
    }
    
    
    public static WareFactory getInstance() {
        if(instance == null){
            instance = new WareFactory();
        }
        return instance;
    }
}
