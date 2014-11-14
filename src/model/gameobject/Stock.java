package model.gameobject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Stock {
    
    private HashMap<Material, Integer> materials;
    private HashMap<Entity, Integer> entities;

    
    public Stock() {
        materials = new HashMap<Material, Integer>();
        entities = new HashMap<Entity, Integer>();
    }
    
    
    public void addMaterial(Material material, int amount)
    {
        materials.put(material, amount);
    }
    
    
    public void addEntity(Entity entity, int amount)
    {
        entities.put(entity, amount);
    }
    
    
    /**
     * printing the list of items in this stock (just for testing purpose)
     */
    public void listStock()
    {
        Iterator it = materials.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Material material = (Material)pairs.getKey();
            Integer amount = (Integer) pairs.getValue();
            System.out.println(material.getName()+" "+amount);
            it.remove();
        }
        
        it = entities.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Entity entity = (Entity)pairs.getKey();
            Integer amount = (Integer) pairs.getValue();
            System.out.println(entity.getName()+" "+amount);
            it.remove();
        }
        
    }
    
    
}
