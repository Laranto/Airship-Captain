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
        if(this.harbor == null)
        {
            //for testing purpose
            this.harbor = new Harbor(new Market(), new Point(100, 100), true);
        }
        return this.harbor;
    }
    
    
    
}
