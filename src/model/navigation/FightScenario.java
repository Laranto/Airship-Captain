package model.navigation;

import view.FightPanel;
import controller.WindowController;
import model.gameobject.Captain;

public class FightScenario extends Scenario {

    public FightScenario(String description, Captain captain) {
        super(description, captain);
    }

    @Override
    public void show() {
        WindowController.showPanel(new FightPanel(this));
    }

}
