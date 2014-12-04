package view;

import handler.TableStockModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

import model.economy.Stock;
import model.navigation.TradeScenario;
import common.Character;
import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;
import controller.WindowController;

public class TradeScenarioPanel extends MarketPanel {
    private static final long serialVersionUID = 1L;
    private Stock stock = null;
    private TradeScenario scenario;

    public TradeScenarioPanel(TradeScenario scenario) {
        this.scenario = scenario;
        
    }


    @Override
    protected JButton createButton() {
        try {
            this.icon = new ImageIcon(FileUtils.loadImage(new File(Constants.COMPASS_IMAGE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton navigation = new JButton(MenuItemEnum.NAVIGATION_MAP.text());
        navigation.setIcon(icon);
        navigation.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                scenario.setActive(false);
                WindowController.showNavigation();
            }
        });
        return navigation;
    }
    
    @Override
    protected JTable getOpponentTable() {
        JTable opponentTable = new JTable(new TableStockModel(Character.OPPONENT){
            @Override
            protected Stock getStock() {
                if(stock == null){
                    stock = new Stock();
                }
                return stock;
            }
        });
        return opponentTable;
    }
}
