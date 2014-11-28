package view;

import handler.MarketStrategy;
import handler.StockItemTransferHandler;
import handler.TableStockModel;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import model.GameState;
import model.economy.Stock;

import common.Character;
import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;

import controller.ButtonController;

public class MarketPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    private ImageIcon icon;
    private StockItemTransferHandler transferHandler = new StockItemTransferHandler();

    public MarketPanel() {
        this.setLayout(null);
        
        /**
         * Ship Stock list
         */
        JLabel playerNameLabel = new JLabel(GameState.getInstance().getAirship().getName());
        playerNameLabel.setBounds(Constants.WINDOW_WIDTH * 3/100, Constants.WINDOW_HEIGHT * 1/25, Constants.WINDOW_WIDTH * 9/20, 20);
        add(playerNameLabel);
        JTable playerTable = new JTable(new TableStockModel(Character.PLAYER));
        Rectangle positionPlayerTable = new Rectangle(
                Constants.WINDOW_WIDTH * 3/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        addInScrollPane(playerTable, positionPlayerTable);
        
        /**
         * Market Stock list
         */
        JLabel marketNameLabel = new JLabel("Markt");
        marketNameLabel.setBounds(Constants.WINDOW_WIDTH * 50/100, Constants.WINDOW_HEIGHT * 1/25, Constants.WINDOW_WIDTH * 9/20, 20);
        add(marketNameLabel);
        JTable opponentTable = getOpponentTable();
        Rectangle positionOpponentTable = new Rectangle(
                Constants.WINDOW_WIDTH * 50/100, 
                Constants.WINDOW_HEIGHT * 1/12, 
                Constants.WINDOW_WIDTH * 9/20 , 
                Constants.WINDOW_HEIGHT * 2/3);
        addInScrollPane(opponentTable, positionOpponentTable);
        
        /**
         * For placing the harbor button
         */
        addBackButton();
    }

    private void addInScrollPane(JTable table, Rectangle position) {
        table.setBounds(position);
        table.setDragEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDropMode(DropMode.INSERT_ROWS);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(table.getWidth()/10*6-4);
        table.getColumnModel().getColumn(1).setPreferredWidth(table.getWidth()/10*2);
        table.getColumnModel().getColumn(2).setPreferredWidth(table.getWidth()/10*2);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setTransferHandler(transferHandler);
        
        JScrollPane scrollPanePlayerTable = new JScrollPane(table, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePlayerTable.setBounds(position);
        scrollPanePlayerTable.setViewportView(table);
        scrollPanePlayerTable.setTransferHandler(transferHandler);
        add(scrollPanePlayerTable);
    }
    
    private void addBackButton() {
        JButton backButton = createButton();
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        int width = Constants.WINDOW_WIDTH / 6,
            height = (int)backButton.getPreferredSize().getHeight(),
            x = Constants.WINDOW_WIDTH * 3 / 4,
            y = Constants.WINDOW_HEIGHT-height-40;
        backButton.setBounds(x, y, width, height);
        add(backButton);
    }
    
    protected JButton createButton() {
        MarketStrategy marketStrategy = new MarketStrategy();
        ButtonController buttonController = new ButtonController(marketStrategy);
        try {
            this.icon = new ImageIcon(FileUtils.loadImage(new File(Constants.HARBOR_ICON)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JButton harbor = new JButton(MenuItemEnum.HARBOR.text());
        harbor.setIcon(icon);
        harbor.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.HARBOR);
        harbor.addActionListener(buttonController);
        return harbor;
    }
    
    
    protected JTable getOpponentTable() {
        JTable opponentTable = new JTable(new TableStockModel(Character.OPPONENT){
            @Override
            protected Stock getStock() {
                return GameState.getInstance().getCurrentHarbor().getMarket().getStock();
            }
        });
        return opponentTable;
    }
}
