package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;

import javax.swing.JButton;

import model.GameState;
import model.gameobject.Airship;

import common.Constants;
import common.FileUtils;
import common.enums.PropertyEnum;

public class ShipExportController implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        Object idProperty = clickedButton.getClientProperty(Constants.BUTTON_PROPERTY_ID);
        if (idProperty == PropertyEnum.EXPORT) {
            executeExport();
        } else if (idProperty == PropertyEnum.IMPORT) {
            executeImport();
        }
    }

    private void executeImport() {
        String fileName = WindowController.showFileChooser(Constants.FOLDER_GAME_DATA, "Airship Captain Game Datei", Constants.FILE_ENDNG_GAME, false);
        if (fileName != null)
        {
            File choosenFile = new File(fileName);
            fileName = choosenFile.getName().substring(0, choosenFile.getName().length() - Constants.FILE_ENDNG_GAME.length() - 1);
            Serializable readObject = FileUtils.loadObjectFile(Constants.FOLDER_GAME_DATA, fileName, Constants.FILE_ENDNG_GAME);
            if (readObject instanceof Airship) {
                // TODO Load an entire game state instead of just the ship.
                GameState.getInstance().setAirship((Airship) readObject);
            }
        }

    }

    private void executeExport() {
        String fileName = WindowController.showFileChooser(Constants.FOLDER_AIRSHIPS, "Airship Captain Schiff Datei", Constants.FILE_ENDNG_SHIP, true);
        if (fileName != null)
        {
            if (fileName.endsWith("." + Constants.FILE_ENDNG_GAME))
            {
                fileName = fileName.substring(0, fileName.length() - 1 - Constants.FILE_ENDNG_GAME.length());
            }
        }
        FileUtils.saveObjectFile(GameState.getInstance().getAirship(), Constants.FOLDER_GAME_DATA, fileName, Constants.FILE_ENDNG_SHIP);
    }

}
