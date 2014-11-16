package model.economy;

import java.util.HashMap;

public class Stock {
    
    private HashMap<Ware, Integer> wareList;

    
    public Stock() {
        wareList = new HashMap<Ware, Integer>();
    }
    
    public void addTradeableWare(Ware ware, int amount)
    {
            wareList.put(ware, amount);
        
    }
    
    
    public HashMap<Ware, Integer> getWarelist()
    {
        return this.wareList;
    }
}
