package view;

import handler.MarketStrategy;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.economy.Market;
import model.economy.Stock;
import model.economy.Ware;
import model.gameobject.Airship;
import common.Constants;
import common.enums.MenuItemEnum;
import controller.ButtonController;
import controller.InputController;

public class MarketPanel extends GameDefaultPanel {

    private Airship airship;
    private Market market;

    private List<MenuItemEnum> marketButtons;

    public MarketPanel(Airship airship, Market market) {
        this.setLayout(null);
        this.airship = airship;
        this.market = market;
        
        MarketStrategy marketStrategy = new MarketStrategy(this.airship, this.market);
        ButtonController buttonController = new ButtonController(marketStrategy);
        InputController inputController = new InputController(marketStrategy);
        
        /**
         * Ship Stock list
         */

        JList<Ware> shipWareList = new JList<Ware>(this.createDefaultListModel(this.airship.getStock()));
        shipWareList.setBounds(Constants.WINDOW_WIDTH * 3/100, Constants.WINDOW_HEIGHT * 1/12, Constants.WINDOW_WIDTH * 9/20 , Constants.WINDOW_HEIGHT * 2/3);
        
        shipWareList.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.MARKET_ITEM);
        shipWareList.addMouseListener(inputController);
        add(shipWareList);
        
        /**
         * Market Stock list
         */
        
        JList<Ware> marketWareList = new JList<Ware>(this.createDefaultListModel(this.market.getStock()));
        marketWareList.setBounds(Constants.WINDOW_WIDTH * 50/100, Constants.WINDOW_HEIGHT * 1/12, Constants.WINDOW_WIDTH * 9/20 , Constants.WINDOW_HEIGHT * 2/3);
        marketWareList.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.SHIP_ITEM);
        marketWareList.addMouseListener(inputController);
        add(marketWareList);
        
        
        
        
        
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
    
    
    private DefaultListModel<Ware> createDefaultListModel(Stock stock)
    {
        DefaultListModel<Ware> defaultListModel = new DefaultListModel<Ware>();
        
        HashMap<Ware, Integer> map = stock.getWarelist();
        
        for(Map.Entry<Ware, Integer> e : map.entrySet()){
              Ware ware = e.getKey();
              Integer amount = e.getValue();
              defaultListModel.addElement(ware);
        }
        
        return defaultListModel;
    }
}
