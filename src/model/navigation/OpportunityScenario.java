package model.navigation;

import java.util.Random;

import common.Constants;

import controller.WindowController;
import model.GameState;
import model.gameobject.Captain;

/**
 * Defines what to show, if an opportunity starts on a Route.
 * 
 */
public class OpportunityScenario extends Scenario {

    public OpportunityScenario(String description, Captain captain) {
        super(description, captain);
    }

    @Override
    public void show() {
        int value = new Random().nextInt(Constants.OPPORTUNITY_MAX_MONEY);
        WindowController.showMessage("Opportunity Scenario", "Du hast $ "+value+" gefunden");
        GameState.getInstance().getAirship().getCaptain().getMoney().addAmount(value);
        setActive(false);
    }

    
}
