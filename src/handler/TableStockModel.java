package handler;

import java.text.DecimalFormat;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.GameState;
import model.economy.Stock;
import common.Character;

/**
 * Handles the trade between the 2 tables
 */
public class TableStockModel implements TableModel {

    private String[] COLUMN_NAMES = {"Ware", "Anzahl", "Preis"};
    private Class<?>[] COLUMN_CLAZZES = {String.class, Integer.class, String.class};
    private final int FIRST_COLUMN = 0;
    private final int SECOND_COLUMN = 1;
    private final int THIRD_COLUMN = 2;
    private Character character;
    private DecimalFormat df = new DecimalFormat("0.00");

    public TableStockModel(Character character) {
        this.character = character;
    }
    
    /**
     * Has to be Overridden if the stock isn't from the airship 
     * @return Stock of the current Model
     */
    protected Stock getStock() {
        return GameState.getInstance().getAirship().getStock();
    }
    
    public Character getCharacter(){
        return character;
    }

    @Override
    public int getRowCount() {
        return getStock().getWarelist().size();
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
            return getStock().getWarelist().get(rowIndex).getWare();
        case SECOND_COLUMN:
            return getStock().getWarelist().get(rowIndex).getAmount();
        case THIRD_COLUMN:
            return df.format(getStock().getWarelist().get(rowIndex).getWare().getPrice());
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
