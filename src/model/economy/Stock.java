package model.economy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class Stock {
    
    private HashMap<Tradeable, Integer> wareList;

    
    public Stock() {
        wareList = new HashMap<Tradeable, Integer>();
    }
    
    
    public void addTradeableWare(Tradeable ware, int amount)
    {
        //TODO: control whether the ware already exist
        wareList.put(ware, amount);
    }
    
    public HashMap<Tradeable, Integer> getWarelist()
    {
        return this.wareList;
    }
}
