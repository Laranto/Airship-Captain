package model.factory;

import model.GameState;
import model.navigation.OpportunityScenario;
import model.navigation.Scenario;

public class ScenarioFactory {

    public static Scenario build() {
        Scenario szenario = new OpportunityScenario("Dies ist ein Testszenario", GameState.getInstance().getAirship().getCaptain());
        //TODO generate a szenario for the player
        //what are the reaction of the KI
        //how much damage can he take until he gets destroyed/backs down
        szenario.setEnemy(null);
        return szenario;
    }
}
