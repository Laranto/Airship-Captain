package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import common.Constants;
import common.FileUtils;
import model.GameState;
import model.gameobject.Airship;

public class LoadGameListener implements ActionListener {


    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.loadGame("game_01");
    }

    private void loadGame(String filename) {
        Serializable readObject = FileUtils.loadObjectFile(Constants.FOLDER_GAME_DATA , filename , Constants.GAME_FILE_ENDNG);
        if(readObject instanceof Airship){
            //TODO Load an entire game state instead of just the ship.
            GameState.getInstance().setAirship((Airship)readObject);
        }
    }
}
