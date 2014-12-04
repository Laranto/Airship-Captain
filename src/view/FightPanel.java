package view;

import handler.FightStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameState;
import model.gameobject.Airship;
import model.gameobject.entity.Cannonball;
import model.navigation.FightScenario;

import common.Constants;
import common.enums.MenuItemEnum;

import controller.ButtonController;
import controller.InputController;

public class FightPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    private Airship enemy;
    private JPanel controlMenu;
    private FightStrategy strategy;
    
    
    
    public FightPanel(FightScenario scenario) {
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        enemy = scenario.getEnemy();
        strategy = new FightStrategy(scenario);
        addMouseListener(new InputController(strategy));
        addMouseMotionListener(new InputController(strategy));
        
        this.setLayout(null);
        
        controlMenu = new JPanel();
        controlMenu.setDoubleBuffered(true);
        controlMenu.setFocusable(true);
        controlMenu.setBounds(  Constants.WINDOW_WIDTH - Constants.CONTROL_PANEL_WIDTH, 
                                Constants.WINDOW_HEIGHT - Constants.CONTROL_PANEL_HEIGHT , 
                                Constants.CONTROL_PANEL_WIDTH, 
                                Constants.CONTROL_PANEL_HEIGHT);
        controlMenu.setBackground(Constants.COLOR_SKYBLUE);
        add(controlMenu);
        
        /**
         * rotate ship button
         */
        JButton rotateShipButton = new JButton(MenuItemEnum.ROTATE_SHIP.text());
        rotateShipButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.ROTATE_SHIP);
        rotateShipButton.addActionListener(new ButtonController(strategy));
        rotateShipButton.setHorizontalAlignment(SwingConstants.CENTER);
        rotateShipButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        int width = Constants.WINDOW_WIDTH *99/100,
            height = (int)rotateShipButton.getPreferredSize().getHeight(),
            x = Constants.WINDOW_WIDTH * 1 / 10,
            y = Constants.WINDOW_HEIGHT-100;
        rotateShipButton.setBounds(x, y, width, height);

        controlMenu.add(rotateShipButton);
       
        /**
         * escape from battle Button
         */
        JButton exitFight = new JButton(MenuItemEnum.EXIT_FIGHT.text());
        exitFight.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.EXIT_FIGHT);
        exitFight.addActionListener(new ButtonController(strategy));
        exitFight.setHorizontalAlignment(SwingConstants.CENTER);
        exitFight.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            width = Constants.WINDOW_WIDTH *99/100;
            height = (int)exitFight.getPreferredSize().getHeight();
            x = Constants.WINDOW_WIDTH * 3 / 4;
            y = Constants.WINDOW_HEIGHT-100;
        exitFight.setBounds(x, y, width, height);
        
        controlMenu.add(exitFight);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        GameState.getInstance().getAirship().render(g2);
        g2.translate(Constants.WINDOW_WIDTH/2, 0);
        enemy.render(g2);
        controlMenu.repaint();
        g2.translate(-Constants.WINDOW_WIDTH/2, 0);
        for(Cannonball cannonball: strategy.getCannonballs()){
            cannonball.render(g2);
        }
    }
}
