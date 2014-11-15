package model.factory;

import model.gameobject.Captain;
import model.navigation.OpportunityScenario;
import model.navigation.Scenario;

public class ScenarioFactory {

    public static Scenario build(Captain captain) {
        Scenario szenario = new OpportunityScenario("Dies ist ein Testszenario", captain);
        //TODO generate a szenario for the player
        //what are the reaction of the KI
        //how much damage can he take until he gets destroyed/backs down
        szenario.setEnemy(null);
        return szenario;
    }
}
