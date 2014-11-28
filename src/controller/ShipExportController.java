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
        String fileName = WindowController.showFileChooser(Constants.FOLDER_AIRSHIPS, "Airship Captain Schiff Datei", Constants.FILE_ENDING_SHIP, false);
        if (fileName != null)
        {
            File choosenFile = new File(fileName);
            fileName = choosenFile.getName().substring(0, choosenFile.getName().length() - Constants.FILE_ENDING_SHIP.length() - 1);
            Serializable readObject = FileUtils.loadObjectFile(Constants.FOLDER_AIRSHIPS, fileName, Constants.FILE_ENDING_SHIP);
            if (readObject instanceof Airship) {
                GameState.getInstance().setAirship((Airship) readObject);
            }
        }

    }

    private void executeExport() {
        String fileName = WindowController.showFileChooser(Constants.FOLDER_AIRSHIPS, "Airship Captain Schiff Datei", Constants.FILE_ENDING_SHIP, true);
        if (fileName != null)
        {
            if (fileName.endsWith("." + Constants.FILE_ENDING_SHIP))
            {
                fileName = fileName.substring(0, fileName.length() - 1 - Constants.FILE_ENDING_SHIP.length());
            }
            FileUtils.saveObjectFile(GameState.getInstance().getAirship(), Constants.FOLDER_AIRSHIPS, fileName, Constants.FILE_ENDING_SHIP);
        }
    }

}
