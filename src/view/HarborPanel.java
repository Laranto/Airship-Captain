package view;

import handler.HarborStrategy;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.gameobject.Airship;

import common.Constants;
import common.enums.MenuItemEnum;

import controller.ButtonController;

public class HarborPanel extends GameDefaultPanel {

    private Airship airship;

    private List<MenuItemEnum> navigationButtons;

    public HarborPanel(Airship airship) {
        this.setLayout(null);
        
        this.airship = airship;

        HarborStrategy harborStrategy = new HarborStrategy(this.airship);
        ButtonController buttonController = new ButtonController(harborStrategy);


        navigationButtons = new ArrayList<MenuItemEnum>();
        navigationButtons.add(MenuItemEnum.SHIPYARD);
        navigationButtons.add(MenuItemEnum.MARKET);
        navigationButtons.add(MenuItemEnum.SAVE_GAME);
        navigationButtons.add(MenuItemEnum.LOAD_GAME);
        navigationButtons.add(MenuItemEnum.EXIT_GAME);

        GridLayout mainGridLayout = new GridLayout(0, 1);
        mainGridLayout.setVgap(10);

        JPanel navigationGridPanel = new JPanel(mainGridLayout);
        navigationGridPanel.setSize(Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT / 3);

        navigationGridPanel.setLocation(Constants.WINDOW_WIDTH * 3 / 4, Constants.WINDOW_HEIGHT * 1 / 8);
        add(navigationGridPanel);

        for (MenuItemEnum navigationButton : navigationButtons) {

            JButton harborButton = new JButton(navigationButton.text());
            harborButton.putClientProperty(Constants.BUTTON_PROPERTY_ID,
                    navigationButton);
            harborButton.setHorizontalAlignment(SwingConstants.CENTER);
            harborButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            harborButton.addActionListener(buttonController);
            navigationGridPanel.add(harborButton);
        }

    }

}
