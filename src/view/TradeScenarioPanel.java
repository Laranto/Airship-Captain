package view;

import handler.TableStockModel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

import model.economy.Stock;

import common.Character;
import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;

public class TradeScenarioPanel extends MarketPanel {
    private static final long serialVersionUID = 1L;
    private Stock stock = null;
    private ActionListener exitListener;

    public TradeScenarioPanel(ActionListener exitListener){
        this.exitListener = exitListener;
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
        navigation.addActionListener(exitListener);
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
