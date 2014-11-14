package view;

import handler.MarketStrategy;

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

public class MarketPanel extends GameDefaultPanel {

    private Airship airship;
    
    private List<MenuItemEnum> marketButtons;
    
    public MarketPanel(Airship airship) {
        this.setLayout(null);
        this.airship = airship;
        
        MarketStrategy marketStrategy = new MarketStrategy(this.airship);
        ButtonController buttonController = new ButtonController(marketStrategy);
        
        
        /**
         * For placing the harbor button
         */
        JButton harborButton = new JButton(MenuItemEnum.HARBOR.text());
        harborButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.HARBOR);
        harborButton.setHorizontalAlignment(SwingConstants.CENTER);
        harborButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        harborButton.addActionListener(buttonController);
        harborButton.setBounds(Constants.WINDOW_WIDTH/100, Constants.WINDOW_HEIGHT * 85/100, Constants.WINDOW_WIDTH/6, Constants.WINDOW_HEIGHT/11);
        add(harborButton);
        
        /**
         * For placing buy and sell buttons
         */
        
        marketButtons = new ArrayList<MenuItemEnum>();
        marketButtons.add(MenuItemEnum.BUY);
        marketButtons.add(MenuItemEnum.SELL);
        
        GridLayout mainGridLayout = new GridLayout(0, 2);
        mainGridLayout.setHgap(10);

        JPanel navigationGridPanel = new JPanel(mainGridLayout);
        navigationGridPanel.setSize(Constants.WINDOW_WIDTH / 3, Constants.WINDOW_HEIGHT / 11);
        navigationGridPanel.setLocation(Constants.WINDOW_WIDTH * 3 / 5, Constants.WINDOW_HEIGHT * 6 / 7);
        add(navigationGridPanel);

        for (MenuItemEnum marketButtonItem : marketButtons) {

            JButton marketButton = new JButton(marketButtonItem.text());
            marketButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, marketButtonItem);
            marketButton.setHorizontalAlignment(SwingConstants.CENTER);
            marketButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            marketButton.addActionListener(buttonController);
            navigationGridPanel.add(marketButton);
        }
        
        
    }
    
}
