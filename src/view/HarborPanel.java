package view;

import handler.HarborStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;

import controller.ButtonController;

public class HarborPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private ImageIcon icon;
    private List<MenuItemEnum> navigationButtons;

    public HarborPanel() {
        this.setLayout(null);
        
        try {
            this.image = FileUtils.loadImage(new File(Constants.HARBOR_BACKGROUND_IMAGE));
            this.icon = new ImageIcon(FileUtils.loadImage(new File(Constants.COMPASS_IMAGE)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HarborStrategy harborStrategy = new HarborStrategy();
        ButtonController buttonController = new ButtonController(harborStrategy);


        navigationButtons = new ArrayList<MenuItemEnum>();
        navigationButtons.add(MenuItemEnum.SHIPYARD);
        navigationButtons.add(MenuItemEnum.MARKET);
        navigationButtons.add(MenuItemEnum.SAVE_GAME);
        navigationButtons.add(MenuItemEnum.LOAD_GAME);
        navigationButtons.add(MenuItemEnum.SETTINGS);
        navigationButtons.add(MenuItemEnum.EXIT_GAME);

        GridLayout mainGridLayout = new GridLayout(0, 1);
        mainGridLayout.setVgap(10);

        JPanel navigationGridPanel = new JPanel(mainGridLayout);
        navigationGridPanel.setOpaque(false);
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
        
        addNavigationButton(buttonController);
    }

    public void addNavigationButton(ButtonController buttonController) {
        JButton compassButton = new JButton(MenuItemEnum.NAVIGATION_MAP.text());
        compassButton.setIcon(icon);
        int width = Constants.WINDOW_WIDTH / 6,
            height = (int)compassButton.getPreferredSize().getHeight(),
            x = Constants.WINDOW_WIDTH * 3 / 4,
            y = Constants.WINDOW_HEIGHT-height-40;
        compassButton.setHorizontalAlignment(SwingConstants.CENTER);
        compassButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        compassButton.putClientProperty(Constants.BUTTON_PROPERTY_ID,
                MenuItemEnum.NAVIGATION_MAP);
        compassButton.addActionListener(buttonController);
        compassButton.setBounds(x, y, width, height);
        add(compassButton);
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