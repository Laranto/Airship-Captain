package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import common.Constants;
import common.FileUtils;
import model.GameState;
import model.gameobject.Airship;

public class LoadGameListener implements ActionListener {

    private Airship airship;
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.loadGame("game_01");
    }

    private void loadGame(String filename) {
        InputStream fileInputStream = null;
        ObjectInputStream ois = null;

        try {
            fileInputStream = new FileInputStream(Constants.FOLDER_GAME_DATA+ filename + "."+Constants.GAME_FILE_ENDNG);

            /*
             * The objects which should be loaded
             */

            ois = new ObjectInputStream(fileInputStream);
            airship = (Airship) ois.readObject();
            GameState.getInstance().setAirship(airship);
            
            
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
