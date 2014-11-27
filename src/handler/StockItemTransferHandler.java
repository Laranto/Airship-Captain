package handler;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import model.GameState;
import model.economy.StockItem;
import model.economy.StockItemTransferable;
import model.economy.Ware;

import common.Character;

public class StockItemTransferHandler extends TransferHandler {
    private static final int ABBRECHEN = 2;
    private static JTable source;

    @Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof JTable) && 
                support.isDataFlavorSupported(StockItemTransferable.STOCKITEM_FLAVOR);
    }
    
    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                JTable target = (JTable)support.getComponent();
                if(source != target){
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(StockItemTransferable.STOCKITEM_FLAVOR);
                    
                    if (value instanceof StockItem) {
                        StockItem stockItem = (StockItem)value;
                        JFrame parent = new JFrame();
                        JOptionPane optionPane = new JOptionPane();
                        addSlider(optionPane, stockItem.getAmount());
                        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
                        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                        JDialog dialog = optionPane.createDialog(parent, "Transfer");
                        dialog.setVisible(true);
                        
                        
                        if(null != optionPane.getValue() && ABBRECHEN != (int)optionPane.getValue()){
                            if(isPlayer()){
                                GameState.getInstance().getCurrentHarbor().getMarket().
                                        sellItem(
                                                stockItem.getWare(), 
                                                (int)optionPane.getInputValue());
                            }else{
                                GameState.getInstance().getCurrentHarbor().getMarket().
                                        buyItem(
                                                stockItem.getWare(), 
                                                (int)optionPane.getInputValue());
                            }
                        }
                        accept = true;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return accept;
    }

    private boolean isPlayer() {
        return ((TableStockModel)source.getModel()).getCharacter() == Character.PLAYER;
    }
    
    private void addSlider(final JOptionPane optionPane, final int maximum) {
        optionPane.setInputValue(1);
        final JTextField text = new JTextField("1");
        final JSlider slider = new JSlider();
        slider.setMajorTickSpacing(Math.max((int)Math.ceil(maximum/10),1));
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
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }
    
    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof JTable) {
            source = (JTable) c;
            t = new StockItemTransferable(new StockItem(
                    (Ware)source.getModel().getValueAt(source.getSelectedRow(), 0), 
                    (int)source.getModel().getValueAt(source.getSelectedRow(), 1)));
        }
        return t;
    }
    
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
    }
}
