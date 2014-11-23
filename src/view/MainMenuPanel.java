package view;

import handler.HandlerStrategy;
import handler.MainMenuStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameState;
import model.gameobject.Airship;
import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;
import controller.ButtonController;

public class MainMenuPanel extends GameDefaultPanel{
        private List<MenuItemEnum> menuItems;
        private BufferedImage image;
	
	public MainMenuPanel(){
	    setLayout(null);
	    
	    try {
	        this.image = FileUtils.loadImage(new File(Constants.ZEPPLIN_COCKPIT_BACKGROUND_IMAGE));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    HandlerStrategy harborStrategy = new MainMenuStrategy();
	    ButtonController buttonController = new ButtonController(harborStrategy);
	    
            menuItems = new ArrayList<MenuItemEnum>();
            menuItems.add(MenuItemEnum.START_GAME);
            menuItems.add(MenuItemEnum.LOAD_GAME);
            menuItems.add(MenuItemEnum.SETTINGS);
            menuItems.add(MenuItemEnum.EXIT_GAME);
            
            GridLayout mainGridLayout = new GridLayout(0, 1);
            mainGridLayout.setVgap(10);

            JPanel navigationGridPanel = new JPanel(mainGridLayout);
            navigationGridPanel.setOpaque(false);
            navigationGridPanel.setSize(Constants.WINDOW_WIDTH / 6, Constants.WINDOW_HEIGHT / 3);
            navigationGridPanel.setLocation(Constants.WINDOW_WIDTH * 3 / 4, Constants.WINDOW_HEIGHT * 6 / 11);
            add(navigationGridPanel);
            
            for(MenuItemEnum menuItem: menuItems){
                JButton harborButton = new JButton(menuItem.text());
                harborButton.putClientProperty(Constants.BUTTON_PROPERTY_ID,menuItem);
                harborButton.setHorizontalAlignment(SwingConstants.CENTER);
                harborButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
                harborButton.addActionListener(buttonController);
                navigationGridPanel.add(harborButton);
            }
        }
	
	@Override
	public void paintComponent(Graphics g){
	    Graphics2D g2 = (Graphics2D) g;
	    g2.drawImage(
	            image, 
	            0,                              /*start x position*/
	            0,                              /*start y position*/
	            Constants.WINDOW_WIDTH,
	            Constants.WINDOW_HEIGHT, 
	            null                            /*Image Observer*/
	    );
	}
}
