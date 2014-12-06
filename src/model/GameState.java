package model;

import java.io.Serializable;

import model.gameobject.Airship;
import model.navigation.Harbor;

public class GameState implements Serializable{

    private static GameState gameState = null;
    private Airship airship;
    private Harbor harbor;
    
    /**
     * For loading and Testing purposes
     * @param gameState
     */
    public static void setInstance(GameState game) {
        gameState = game;
    }
    
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
    	if(this.harbor != null){
    		this.harbor.setActive(false);
    		this.harbor.setNextDestination(false);
    	}
    	harbor.setActive(true);
    	harbor.setNextDestination(false);
        this.harbor = harbor;
    }
    
    public Harbor getCurrentHarbor()
    {
        return this.harbor;
    }
}
