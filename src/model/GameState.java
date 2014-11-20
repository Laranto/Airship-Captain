package model;

import model.gameobject.Airship;

public class GameState {

    private static GameState gameState = null;
    private Airship airship;
    
    public static GameState getInstance()
    {
        if(GameState.gameState == null)
        {
            GameState.gameState = new GameState();
        }
        
        return GameState.gameState;
    }


    public Airship getAirship() {
        return airship;
    }

    public void setAirship(Airship airship) {
        this.airship = airship;
    }
}
