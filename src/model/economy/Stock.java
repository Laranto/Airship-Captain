package model.economy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stock implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<StockItem> wareList;

    
    public Stock() {
        wareList = new ArrayList<StockItem>();
    }
    
    public StockItem addTradeableWare(Ware ware, int amount) throws Exception
    {
        StockItem stockItem = null;
        if((stockItem = getStockItemByWareName(ware.getName())) != null)
        {
            if(amount < 0 && Math.abs(amount) > stockItem.getAmount()){
                throw new Exception("There are only : "+stockItem.getAmount()+" items this kind left.");
            }
            stockItem.setAmount(stockItem.getAmount()+amount);
            if(stockItem.getAmount() == 0){
                wareList.remove(stockItem);
            }
        }else{
            if(amount < 0){
                throw new Exception("Ware "+ware.getName()+" nicht vorhanden");
            }
            stockItem = new StockItem(new Ware(ware.getName(), ware.getValue(), ware.getWeight(), ware.getPrice()), amount);
            getWarelist().add(stockItem);
        }
        return stockItem;
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
}
