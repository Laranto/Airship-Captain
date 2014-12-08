package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener to close the game.
 * 
 */
public class ExitGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO ask for saving game
        System.exit(0);
    }

}
