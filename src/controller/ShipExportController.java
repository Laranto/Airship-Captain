package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import common.Constants;

public class ShipExportController implements ActionListener {

    public static final Integer EXPORT=0;
    public static final Integer IMPORT=1;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        Object idProperty = clickedButton.getClientProperty(Constants.BUTTON_PROPERTY_ID);
        if(idProperty==EXPORT){
            executeExport();
        }else if(idProperty==IMPORT){
            executeImport();
        }
    }

    private void executeImport() {
        // TODO Auto-generated method stub
        
    }

    private void executeExport() {
        // TODO Auto-generated method stub
        
    }

}
