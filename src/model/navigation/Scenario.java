package model.navigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;

import model.gameobject.Airship;
import model.gameobject.Captain;
import common.Constants;
import common.FileUtils;
import common.Utils;


public abstract class Scenario {

    private String description;
    private boolean isActive;
    private Airship opponent;
    
    public Scenario(String description, Captain captain){
        this.description = description;
        this.isActive = true;
    }
    
    public String getDescription(){
        return description;
    }
    
    public abstract void show();
    public Airship getEnemy() {
        ArrayList<File> fileList = FileUtils.getFileList(Constants.FOLDER_ENEMY_AIRSHIPS);
        String shipName = fileList.get(new Random().nextInt(fileList.size())).getName();
        shipName = shipName.substring(0, shipName.length()-Constants.FILE_ENDING_SHIP.length()-1);
        
        if(this.opponent == null)
        {
            this.opponent = (Airship) FileUtils.loadObjectFile(Constants.FOLDER_ENEMY_AIRSHIPS, shipName, Constants.FILE_ENDING_SHIP);
        }
        return opponent;
    }
    
    public void setEnemy(Airship opponent){
        this.opponent = opponent;
    }
    
    public boolean isActive(){
        return isActive;
    };
    
    public void setActive(boolean isActive){
        this.isActive = isActive;
    }
}
