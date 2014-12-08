package model.navigation;

import model.gameobject.Captain;
import controller.WindowController;

/**
 * Defines what to show, if a trade starts on a Route.
 * 
 */
public class TradeScenario extends Scenario {

    public TradeScenario(String description, Captain captain) {
        super(description, captain);
    }

    @Override
    public void show() {
        WindowController.showTradeScenarioPanel(this);
    }

}
