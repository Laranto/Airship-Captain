package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;

import common.Constants;
import common.FileUtils;

public class SaveGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String fileName = WindowController.showFileChooser(Constants.FOLDER_GAME_DATA, "Airship Captain Game Datei", Constants.FILE_ENDING_GAME,true);

        if(fileName != null)
        {
            if(fileName.endsWith("."+Constants.FILE_ENDING_GAME))
            {
                fileName = fileName.substring(0, fileName.length()-1-Constants.FILE_ENDING_GAME.length());
            }
        }
        
        this.saveGame(fileName);
    }

    private void saveGame(String filename) {
        if(FileUtils.saveObjectFile(GameState.getInstance(), Constants.FOLDER_GAME_DATA, filename, Constants.FILE_ENDING_GAME)){
            WindowController.showMessage("Save Message", "Der Spielstand wurde erfolgreich gespeichert!");
        }else{
            WindowController.showError("Error Message", "Der Spielstand konnte nicht erfolgreich gespeichert werden!");
        }
    }
}
