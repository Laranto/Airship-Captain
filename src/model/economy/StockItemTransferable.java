package model.economy;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StockItemTransferable implements Transferable{
    
    public static final DataFlavor STOCKITEM_FLAVOR = new DataFlavor(StockItem.class, "model.economy.StockItem");
    private StockItem stockItem;

    public StockItemTransferable(StockItem stockItem) {
        this.stockItem = stockItem;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{STOCKITEM_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(STOCKITEM_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        return stockItem;
    }

}
