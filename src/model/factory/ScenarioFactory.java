package model.factory;

import java.util.Random;

import model.GameState;
import model.navigation.FightScenario;
import model.navigation.OpportunityScenario;
import model.navigation.Scenario;
import model.navigation.TradeScenario;

public class ScenarioFactory {

    public static Scenario build() {
        int scenarioTyp = new Random().nextInt(3);
        switch(scenarioTyp){
            case 0:
                return new OpportunityScenario("Dies ist ein OpportunityScenario", GameState.getInstance().getAirship().getCaptain());
            case 1:
                return new FightScenario("Dies ist ein FightScenario", GameState.getInstance().getAirship().getCaptain());
            default: //case 2
                return new TradeScenario("Dies ist ein TradeScenario", GameState.getInstance().getAirship().getCaptain());
        }
    }
}
