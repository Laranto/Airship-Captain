package view;

import handler.MarketStrategy;
import handler.StockItemTransferHandler;
import handler.TableStockModel;

import java.io.File;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import model.economy.Market;
import model.gameobject.Airship;

import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;

import controller.ButtonController;
import controller.InputController;

public class MarketPanel extends GameDefaultPanel {

    private Airship airship;
    private Market market;
    private ImageIcon icon;

    public MarketPanel(Airship airship, Market market) {
        this.setLayout(null);
        this.airship = airship;
        this.market = market;
        
        MarketStrategy marketStrategy = new MarketStrategy(this.airship, this.market);
        ButtonController buttonController = new ButtonController(marketStrategy);
        InputController inputController = new InputController(marketStrategy);
        StockItemTransferHandler transferHandler = new StockItemTransferHandler();
        
        /**
         * Ship Stock list
         */
        JTable playerTable = new JTable(new TableStockModel(this.airship.getStock()));
        playerTable.setBounds(
                Constants.WINDOW_WIDTH * 3/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        playerTable.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.MARKET_ITEM);
        playerTable.addMouseListener(inputController);
        playerTable.setDragEnabled(true);
        playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerTable.setDropMode(DropMode.INSERT_ROWS);
        playerTable.getColumnModel().getColumn(0).setPreferredWidth(playerTable.getWidth()/10*6-4);
        playerTable.getColumnModel().getColumn(1).setPreferredWidth(playerTable.getWidth()/10*2);
        playerTable.getColumnModel().getColumn(2).setPreferredWidth(playerTable.getWidth()/10*2);
        playerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        playerTable.setTransferHandler(transferHandler);
        
        JScrollPane scrollPanePlayerTable = new JScrollPane(playerTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePlayerTable.setBounds(
                Constants.WINDOW_WIDTH * 3/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        scrollPanePlayerTable.setViewportView(playerTable);
        scrollPanePlayerTable.setTransferHandler(transferHandler);
        add(scrollPanePlayerTable);
        /**
         * Market Stock list
         */
        
        JTable opponentTable = new JTable(new TableStockModel(this.market.getStock()));
        opponentTable.setBounds(
                Constants.WINDOW_WIDTH * 50/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        opponentTable.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.MARKET_ITEM);
        opponentTable.addMouseListener(inputController);
        opponentTable.setDragEnabled(true);
        opponentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opponentTable.setDropMode(DropMode.INSERT_ROWS);
        opponentTable.getColumnModel().getColumn(0).setPreferredWidth(opponentTable.getWidth()/10*6-4);
        opponentTable.getColumnModel().getColumn(1).setPreferredWidth(opponentTable.getWidth()/10*2);
        opponentTable.getColumnModel().getColumn(2).setPreferredWidth(opponentTable.getWidth()/10*2);
        opponentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        opponentTable.setTransferHandler(transferHandler);
        
        JScrollPane scrollPaneOpponentTable = new JScrollPane(opponentTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneOpponentTable.setBounds(
                Constants.WINDOW_WIDTH * 50/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        scrollPaneOpponentTable.setViewportView(opponentTable);
        scrollPaneOpponentTable.setTransferHandler(transferHandler);
        add(scrollPaneOpponentTable);
        
        
        
        /**
         * For placing the harbor button
         */
        try {
            this.icon = new ImageIcon(FileUtils.loadImage(new File(Constants.HARBOR_ICON)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JButton harbor = new JButton(MenuItemEnum.HARBOR.text());
        harbor.setIcon(icon);
        harbor.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.HARBOR);
        harbor.addActionListener(buttonController);
        harbor.setHorizontalAlignment(SwingConstants.CENTER);
        harbor.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        int width = Constants.WINDOW_WIDTH / 6,
            height = (int)harbor.getPreferredSize().getHeight(),
            x = Constants.WINDOW_WIDTH * 3 / 4,
            y = Constants.WINDOW_HEIGHT-height-40;
        harbor.setBounds(x, y, width, height);
        add(harbor);
    }
}
