package model.economy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable{
    
    private List<StockItem> wareList;

    
    public Stock() {
        wareList = new ArrayList<StockItem>();
    }
    
    public void addTradeableWare(Ware ware, int amount) throws Exception
    {
        StockItem stockItem = null;
        if((stockItem = getStockItemByWareName(ware.getName())) != null)
        {
            if(amount < 0 && Math.abs(amount) > stockItem.getAmount()){
                throw new Exception("There are only : "+stockItem.getAmount()+" items this kind left.");
            }
            if(stockItem == null && amount < 0){
                throw new Exception("Ware "+ware.getName()+" nicht vorhanden");
            }
            stockItem.setAmount(stockItem.getAmount()+amount);
            if(stockItem.getAmount() == 0){
                wareList.remove(stockItem);
            }
        }else{
            stockItem = new StockItem(ware, amount, ware.getPrice());
            getWarelist().add(stockItem);
        }
        Economy.calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue());
    }
    
    public StockItem getStockItemByWareName(String name){
        for(StockItem stockItem: wareList){
            if(stockItem.getWare().getName().equals(name)){
                return stockItem;
            }
        }
        return null;
    }
    
    public List<StockItem> getWarelist()
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
            for(StockItem stockItem: wareList){
                System.out.println(stockItem.getWare().getName()+"\t"+stockItem.getWare()+"\t"+stockItem.getAmount()+"\t\t\t"+stockItem.getWare().getPrice());
            }
        }
    }
}
