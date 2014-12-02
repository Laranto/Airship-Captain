package model.navigation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TradeScenarioPanel;
import controller.WindowController;
import model.gameobject.Captain;

public class TradeScenario extends Scenario {

    public TradeScenario(String description, Captain captain) {
        super(description, captain);
    }

    @Override
    public void show() {
        WindowController.showPanel(new TradeScenarioPanel(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setActive(false);
                    WindowController.showNavigation();
                }
        }));
    }

}
