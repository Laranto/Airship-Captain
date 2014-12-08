package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener to start the game
 * 
 */
public class StartGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowController.showConstruction();  
    }

}
