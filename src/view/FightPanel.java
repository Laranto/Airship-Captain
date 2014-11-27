package view;

import handler.FightStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.GameState;
import model.factory.ScenarioFactory;
import model.gameobject.Airship;
import model.navigation.Scenario;

import common.Constants;

import controller.InputController;

public class FightPanel extends GameDefaultPanel {
    private Airship enemy;
    
    
    
    public FightPanel() {
        Scenario szenario = ScenarioFactory.build();
        enemy = szenario.getEnemy();
        
        FightStrategy strategy = new FightStrategy();
        addMouseListener(new InputController(strategy));
        addMouseMotionListener(new InputController(strategy));
        
        

        GridLayout mainLayout = new GridLayout(1, 2);
        this.setLayout(mainLayout);

        JPanel shipView = new JPanel();
        this.add(shipView);
        
        JPanel enemyView = new JPanel();
        this.add(enemyView);
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
