package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;

import common.Constants;
import common.FileUtils;

public class SaveGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.saveGame("game_01");
    }

    private void saveGame(String filename) {
        
        FileUtils.saveObjectFile(GameState.getInstance().getAirship(), Constants.FOLDER_GAME_DATA, filename, Constants.FILE_ENDNG_GAME);
    }
}
