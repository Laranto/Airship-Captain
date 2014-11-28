package model.navigation;

import common.Constants;
import common.FileUtils;
import model.gameobject.Airship;
import model.gameobject.Captain;


public abstract class Scenario {

    private String description;
    private Airship opponent;
    
    public Scenario(String description, Captain captain){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Airship getEnemy() {
        if(this.opponent == null)
        {
            //TODO: has to be adjusted, for example for random enemy
            this.opponent = (Airship) FileUtils.loadObjectFile(Constants.FOLDER_AIRSHIPS, "enemy01", Constants.FILE_ENDNG_SHIP);
        }
        return opponent;
    }
    
    public void setEnemy(Airship opponent){
        this.opponent = opponent;
    }
}
