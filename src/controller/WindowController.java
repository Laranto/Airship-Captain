package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Airship;
import view.ConstructionPanel;
import view.FightPanel;
import view.HarborPanel;
import view.MainMenuPanel;
import view.NavigationPanel;

/**
 *  Controls which panel is being displayed.
 */
public class WindowController{

    private static JFrame container;
    private static JPanel mainMenu;
    private static JPanel constructionPanel;
    private static JPanel fightPanel;
    private static JPanel navigationPanel;
    private static JPanel harborPanel;
    
    public static void setFrame(JFrame frame){
    	container = frame;
    }
    
    public static void showMainMenu(){
    	if(mainMenu == null){
    		mainMenu = new MainMenuPanel();
    	}
    	showPanel(mainMenu);
    }
    
    public static void showConstruction(Airship airship){
    	if(constructionPanel == null){
    		constructionPanel = new ConstructionPanel(airship);
    	}
    	showPanel(constructionPanel);
    }
    
    public static void showFight(){
    	if(fightPanel == null){
    		fightPanel = new FightPanel();
    	}
    	showPanel(fightPanel);
    }
    
    public static void showNavigation(){
    	if(navigationPanel == null){
    		navigationPanel = new NavigationPanel();
    	}
    	showPanel(navigationPanel);
    }
    
    public static void showHarbor(){
    	if(harborPanel == null){
    		harborPanel = new HarborPanel();
    	}
    	showPanel(harborPanel);
    }
    
    private static void showPanel(JPanel panel) {
		container.setContentPane(panel);
		container.paintAll(container.getGraphics());
	}
}
