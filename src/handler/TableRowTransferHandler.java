package handler;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.economy.StockItem;
import model.economy.Ware;

public class TableRowTransferHandler extends TransferHandler {

    private static final int ABBRECHEN = 2;
    private final DataFlavor localObjectFlavor = new DataFlavor(StockItem.class, "StockItem");
    private static JTable source;

    public TableRowTransferHandler() {
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        assert (c instanceof JTable);
        source = (JTable)c;
        return new DataHandler(new StockItem(
                (Ware)((JTable)c).getModel().getValueAt(((JTable)c).getSelectedRow(), 0), 
                (Integer)((JTable)c).getValueAt(((JTable)c).getSelectedRow(), 1), 
                (Float)((JTable)c).getValueAt(((JTable)c).getSelectedRow(), 2)),
                localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        boolean b = info.isDataFlavorSupported(localObjectFlavor);
        return b;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        JTable target = (JTable) info.getComponent();
        try {
            if(source != target){
                StockItem stockItem = (StockItem)info.getTransferable().getTransferData(localObjectFlavor);
                JFrame parent = new JFrame();
                JOptionPane optionPane = new JOptionPane();
                addSlider(optionPane, stockItem.getAmount());
                optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
                optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                JDialog dialog = optionPane.createDialog(parent, "Transfer");
                dialog.setVisible(true);
                
                
                if(null != optionPane.getValue() && ABBRECHEN != (int)optionPane.getValue()){
                    ((TableStockModel)source.getModel()).addItem(stockItem.getWare(), -1*(int)optionPane.getInputValue());
                    ((TableStockModel)target.getModel()).addItem(stockItem.getWare(), (int)optionPane.getInputValue());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    static void addSlider(final JOptionPane optionPane, final int maximum) {
        optionPane.setInputValue(1);
        final JTextField text = new JTextField("1");
        final JSlider slider = new JSlider();
        slider.setMajorTickSpacing((int)Math.ceil(maximum/10));
        slider.setMaximum(maximum);
        slider.setValue(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener changeListener = new ChangeListener() {
          public void stateChanged(ChangeEvent changeEvent) {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
              optionPane.setInputValue(new Integer(theSlider.getValue()));
              text.setText(String.valueOf(theSlider.getValue()));
            }
          }
        };
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = text.getText();
                slider.setValue(1);
                if(!typed.matches("\\d*")) {
                    return;
                }
                int number = Integer.parseInt(typed);
                if(number > maximum){
                    number = maximum;
                    text.setText(String.valueOf(number));
                }
                slider.setValue(number);
            }
        });
        slider.addChangeListener(changeListener);
        optionPane.setMessage(new Object[] { "Wieviele Einheiten: ", slider,text});
      }

    @Override
    protected void exportDone(JComponent c, Transferable t, int act) {
        if (act == TransferHandler.MOVE) {
            ((JTable)c).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}