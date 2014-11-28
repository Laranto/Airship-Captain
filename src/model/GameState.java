package model;

import java.awt.Point;

import model.economy.Market;
import model.gameobject.Airship;
import model.navigation.Harbor;

public class GameState {

    private static GameState gameState = null;
    private Airship airship;
    private Harbor harbor;
    
    public static GameState getInstance()
    {
        if(GameState.gameState == null)
        {
            GameState.gameState = new GameState();
        }
        
        return GameState.gameState;
    }


    public Airship getAirship() {
        if(this.airship == null)
        {
            this.airship = new Airship();
        }
        
        return this.airship;
    }

    public void setAirship(Airship airship) {
        this.airship = airship;
    }
    
    public void setCurrentHarbor(Harbor harbor)
    {
        this.harbor = harbor;
    }
    
    public Harbor getCurrentHarbor()
    {
        return this.harbor;
    }
    
    
    
}
