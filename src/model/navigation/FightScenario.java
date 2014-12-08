package model.navigation;

import view.FightPanel;
import controller.WindowController;
import model.gameobject.Captain;

/**
 * Defines what to show, if a fight starts on a Route.
 * 
 */
public class FightScenario extends Scenario {

    public FightScenario(String description, Captain captain) {
        super(description, captain);
    }

    @Override
    public void show() {
        WindowController.showPanel(new FightPanel(this));
    }

}
