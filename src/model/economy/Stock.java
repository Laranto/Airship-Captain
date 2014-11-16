package model.economy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
    
    /**
     * Printing the state of the market in the console
     */
    public void printStock()
    {
        System.out.println("Name\t\t\t\tReferenz\t\t\t\t\t\t\t\tAnzahl\t\t\tPreis");
        
        if(this.getWarelist().isEmpty())
        {
            System.out.println("Stock is empty");
        }else{
            Iterator<Entry<Ware, Integer>> it = this.getWarelist().entrySet().iterator();
            while (it.hasNext()) {
                Entry<Ware, Integer> pairs = it.next();
                Ware ware = pairs.getKey();
                Integer amount = pairs.getValue();
                System.out.println(ware.getName()+"\t"+ware +"\t"+amount+"\t\t\t"+ware.getPrice());
            }
        }
    }
}
