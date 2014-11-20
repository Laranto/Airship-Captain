package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.GameState;
import model.gameobject.Airship;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import common.Constants;

public class SaveGameListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.saveGame("game_01");
    }

    private void saveGame(String filename) {
        OutputStream fileOutputStream = null;


        try {
            fileOutputStream = new FileOutputStream(Constants.FOLDER_GAME_DATA+ filename + "."+Constants.GAME_FILE_ENDNG);
            ObjectOutputStream o = new ObjectOutputStream(fileOutputStream);
            o.writeObject(GameState.getInstance().getAirship());
            
            

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
