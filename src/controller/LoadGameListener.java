package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;

import com.sun.glass.ui.Window;

import model.GameState;
import model.gameobject.Airship;
import common.Constants;
import common.FileUtils;

public class LoadGameListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String choosenFilePath = WindowController.showFileChooser(Constants.FOLDER_GAME_DATA, "Airship Captain Game Datei", Constants.FILE_ENDNG_GAME);
        if(choosenFilePath != null)
        {
            File choosenFile = new File(choosenFilePath);
            String fileNameWithoutExtension = choosenFile.getName().substring(0, choosenFile.getName().length()-Constants.FILE_ENDNG_GAME.length()-1);
            this.loadGame(fileNameWithoutExtension);
        }
    }

    private void loadGame(String filename) {
        Serializable readObject = FileUtils.loadObjectFile(Constants.FOLDER_GAME_DATA , filename , Constants.FILE_ENDNG_GAME);
        if(readObject instanceof Airship){
            //TODO Load an entire game state instead of just the ship.
            GameState.getInstance().setAirship((Airship)readObject);
        }
    }
}
