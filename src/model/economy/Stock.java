package model.economy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.factory.WareFactory;

import common.Constants;
import common.Utils;

public class Stock implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<StockItem> wareList;

    
    public Stock(boolean emptyStock)
    {
        wareList = new ArrayList<StockItem>();
        if(!emptyStock)
        {
            initStock();
        }
    }
    
    public Stock() {
        this(false);
    }
    
    /*
     * initializing the stock randomly
     */
    private void initStock() {
        if(wareList.isEmpty())
        {
            ArrayList<Ware> wares = WareFactory.getInstance().getWares();
            try{
                StockItem stockItem = null;
                for (Ware ware: wares) {
                    stockItem = addTradeableWare( ware, Utils.getRandomIntBetween(10,  (int) Constants.WARE_STANDARD_AMOUNT * 2));
                    stockItem.getWare().setPrice(Economy.calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue()));
                }
                
                stockItem = addTradeableWare( wares.get(0), 200 );
                stockItem.getWare().setPrice(Economy.calculatePrice(stockItem.getAmount(), stockItem.getWare().getValue()));
                
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
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
