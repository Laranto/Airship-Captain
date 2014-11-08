package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Airship;

public class StartGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowController.showConstruction(new Airship());  
    }

}
