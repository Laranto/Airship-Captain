package model.navigation;

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
        return opponent;
    }
    
    public void setEnemy(Airship opponent){
        this.opponent = opponent;
    }
}
