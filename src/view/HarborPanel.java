package view;

import handler.HarborStrategy;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Airship;
import model.enums.MENU;

import common.Constants;

import controller.ButtonController;

public class HarborPanel extends JPanel {

    private Airship airship;

    private List<MENU> navigationButtons;

    public HarborPanel(Airship airship) {
        this.airship = airship;

        HarborStrategy harborStrategy = new HarborStrategy(this.airship);
        ButtonController buttonController = new ButtonController(harborStrategy);

        this.setLayout(null);
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        navigationButtons = new ArrayList<MENU>();
        navigationButtons.add(MENU.SHIPYARD);
        navigationButtons.add(MENU.MARKET);
        navigationButtons.add(MENU.SAVE_GAME);
        navigationButtons.add(MENU.LOAD_GAME);
        navigationButtons.add(MENU.EXIT_GAME);

        GridLayout mainGridLayout = new GridLayout(0, 1);
        mainGridLayout.setVgap(10);

        JPanel navigationGridPanel = new JPanel(mainGridLayout);
        navigationGridPanel.setSize(Constants.WINDOW_WIDTH / 6,
                Constants.WINDOW_HEIGHT / 3);

        navigationGridPanel.setLocation(Constants.WINDOW_WIDTH * 3 / 4,
                Constants.WINDOW_HEIGHT * 1 / 8);
        add(navigationGridPanel);

        for (MENU navigationButton : navigationButtons) {

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