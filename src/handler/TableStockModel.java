package handler;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.economy.Stock;
import model.economy.Ware;

public class TableStockModel implements TableModel {

    private Stock stock;
    private String[] COLUMN_NAMES = {"Ware", "Anzahl", "Preis"};
    private Class<?>[] COLUMN_CLAZZES = {String.class, Integer.class, Float.class};
    private final int FIRST_COLUMN = 0;
    private final int SECOND_COLUMN = 1;
    private final int THIRD_COLUMN = 2;

    public TableStockModel(Stock stock) {
        this.stock = stock;
    }

    public void addItem(Ware ware, Integer amount) throws Exception {
        stock.addTradeableWare(ware, amount);
    }

    @Override
    public int getRowCount() {
        return stock.getWarelist().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_CLAZZES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
        case FIRST_COLUMN:
            return stock.getWarelist().get(rowIndex).getWare();
        case SECOND_COLUMN:
            return stock.getWarelist().get(rowIndex).getAmount();
        case THIRD_COLUMN:
            return stock.getWarelist().get(rowIndex).getWare().getPrice();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
