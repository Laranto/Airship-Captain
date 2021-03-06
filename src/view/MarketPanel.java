package view;

import handler.MarketStrategy;
import handler.StockItemTransferHandler;
import handler.TableStockModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
import model.economy.Economy;
import model.economy.Stock;
import model.economy.StockItem;
import common.Character;
import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;
import controller.ButtonController;

public class MarketPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    protected ImageIcon icon;
    private BufferedImage image;
    private StockItemTransferHandler transferHandler = new StockItemTransferHandler();
    private boolean isCreated = false;
    
    //TODO: ask arni or suter about this problem
    private static JLabel moneyLabel = new JLabel("");
    
    
    public MarketPanel() {}
    
    public void create(){
        this.isCreated=true;
        try {
            this.image = FileUtils.loadImage(new File(Constants.MARKET_BACKGROUND_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        this.setLayout(null);
        
        /**
         * Label for displaying the money
         */
        moneyLabel = getLabel("", 
                                        Constants.WINDOW_WIDTH * 10/100, 
                                        Constants.WINDOW_HEIGHT * 21/25, 
                                        36);
        add(moneyLabel);
        
        
        /**
         * Ship Stock list
         */
        add(getLabel(GameState.getInstance().getAirship().getName(), Constants.WINDOW_WIDTH * 3/100, Constants.WINDOW_HEIGHT * 1/25, 20));

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
        add(getLabel("Markt", Constants.WINDOW_WIDTH * 50/100, Constants.WINDOW_HEIGHT * 1/25, 20));
        
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
    
    private JLabel getLabel(String labelValue, int x, int y, int fontSize)
    {
        JLabel label = new JLabel(labelValue);
        label.setBounds(x, y, Constants.WINDOW_WIDTH * 9/20, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        return (label);
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
    
    
    private JTable getOpponentTable() {
        adjustAirshipPrices();
        JTable opponentTable = new JTable(new TableStockModel(Character.OPPONENT){
            @Override
            protected Stock getStock() {
                return getOpponentStock();
            }
        });
        return opponentTable;
    }
    
    protected Stock getOpponentStock(){
        return GameState.getInstance().getCurrentHarbor().getMarket().getStock();
    }
    
    private void adjustAirshipPrices() {
        StockItem airshipItem = null;
        for(StockItem marketItem: getOpponentStock().getWarelist()){
            if((airshipItem = getItemOnAirship(marketItem)) != null){
                Economy.calculateNewPriceFor(marketItem, airshipItem);
            }
        }
    }

    private StockItem getItemOnAirship(StockItem stockItem) {
        return GameState.getInstance().getAirship().getStock().getStockItemByWareName(stockItem.getWare().getName());
    }

    @Override
    public void paintComponent(Graphics g){
        if(!isCreated){
            create();
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(
                image, 
                0,                              /*start x position*/
                0,                              /*start y position*/
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT, 
                null                            /*Image Observer*/
        );
        
        //updating the moneyLabel
        moneyLabel.setText("$ "+ (GameState.getInstance().getAirship().getCaptain().getMoney().getAmount())  );
    }
    
}
