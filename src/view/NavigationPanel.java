package view;

import handler.HandlerStrategy;
import handler.NavigationStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.navigation.Harbor;

import common.Constants;
import common.FileUtils;
import common.enums.MenuItemEnum;

import controller.ButtonController;
import controller.InputController;


public class NavigationPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private ImageIcon icon;
    private HandlerStrategy strategy = null;
    

    public NavigationPanel() {
        try {
            this.image = FileUtils.loadImage(new File(Constants.NAVIGATION_BACKGROUND_IMAGE));
            this.icon = new ImageIcon(FileUtils.loadImage(new File(Constants.HARBOR_ICON)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        strategy = new NavigationStrategy();
        InputController inputController = new InputController(strategy);
        ButtonController buttonController = new ButtonController(strategy);
        addMouseListener(inputController);
        addMouseMotionListener(inputController);
        addKeyListener(inputController);
        this.setFocusable(true);
        
        setLayout(null);
        
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
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        ((NavigationStrategy)strategy).getRoute().removeLines(g2);
        g2.drawImage(
                image, 
                0,                              /*start x position*/
                0,                              /*start y position*/
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT, 
                null                            /*Image Observer*/
        );
        for(Harbor h: Constants.HARBORS){
            h.render(g2);
        }
        ((NavigationStrategy)strategy).getRoute().render(g2);
    }
}