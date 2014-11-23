package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowController.showConstruction();  
    }

}
