package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.GameState;

import common.Constants;
import common.FileUtils;

public class SaveGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.saveGame("game_01");
    }

    private void saveGame(String filename) {
        
        OutputStream fileOutputStream = null;
        ObjectOutputStream ois = null;

        try {
            fileOutputStream = new FileOutputStream(Constants.FOLDER_GAME_DATA+ filename + "."+Constants.GAME_FILE_ENDNG);
            ois = new ObjectOutputStream(fileOutputStream);
            ois.writeObject(GameState.getInstance().getAirship());
            
            

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                ois.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
