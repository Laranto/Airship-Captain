package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;

import model.GameState;

import common.Constants;
import common.FileUtils;

/**
 * Listener to load an existing game.
 *
 */
public class LoadGameListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String choosenFilePath = WindowController.showFileChooser(Constants.FOLDER_GAME_DATA, "Airship Captain Game Datei", Constants.FILE_ENDING_GAME,false);
        if(choosenFilePath != null)
        {
            File choosenFile = new File(choosenFilePath);
            String fileNameWithoutExtension = choosenFile.getName().substring(0, choosenFile.getName().length()-Constants.FILE_ENDING_GAME.length()-1);
            this.loadGame(fileNameWithoutExtension);
        }
    }

    private void loadGame(String filename) {
        Serializable readObject = FileUtils.loadObjectFile(Constants.FOLDER_GAME_DATA , filename , Constants.FILE_ENDING_GAME);
        GameState.setInstance((GameState)readObject);
        WindowController.showHarbor();
    }
}
