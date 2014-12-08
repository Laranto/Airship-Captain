package model.navigation;

import view.FightPanel;
import controller.WindowController;
import model.gameobject.Captain;

/**
 *  Scenario to handle not so friendly meetings with other travelers
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
