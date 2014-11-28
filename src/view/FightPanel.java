package view;

import handler.FightStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameState;
import model.factory.ScenarioFactory;
import model.gameobject.Airship;
import model.navigation.Scenario;
import common.Constants;
import common.enums.MenuItemEnum;
import controller.ButtonController;
import controller.InputController;

public class FightPanel extends GameDefaultPanel {
    private static final long serialVersionUID = 1L;
    private Airship enemy;
    
    
    
    public FightPanel() {
        Scenario szenario = ScenarioFactory.build();
        enemy = szenario.getEnemy();
        
        FightStrategy strategy = new FightStrategy();
        addMouseListener(new InputController(strategy));
        addMouseMotionListener(new InputController(strategy));
        
        this.setLayout(null);
        
        

        GridLayout mainLayout = new GridLayout(1, 2);
        JPanel battleField = new JPanel(mainLayout);
        battleField.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT*1/20);
        add(battleField);

        JPanel shipView = new JPanel();
        battleField.add(shipView);
        
        JPanel enemyView = new JPanel();
        battleField.add(enemyView);

        JButton exitFight = new JButton(MenuItemEnum.EXIT_FIGHT.text());
        exitFight.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.EXIT_FIGHT);
        exitFight.addActionListener(new ButtonController(strategy));
        exitFight.setHorizontalAlignment(SwingConstants.CENTER);
        exitFight.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        int width = Constants.WINDOW_WIDTH *99/100,
            height = (int)exitFight.getPreferredSize().getHeight(),
            x = Constants.WINDOW_WIDTH * 3 / 4,
            y = Constants.WINDOW_HEIGHT-100;
        exitFight.setBounds(x, y, width, height);
        
        add(exitFight);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        GameState.getInstance().getAirship().render(g2);
        g2.translate(Constants.WINDOW_WIDTH/2, 0);
        enemy.render(g2);
    }
}
