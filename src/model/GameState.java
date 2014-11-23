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
        if(this.airship == null)
        {
            this.airship = new Airship();
        }
        
        return this.airship;
    }

    public void setAirship(Airship airship) {
        this.airship = airship;
    }
}
