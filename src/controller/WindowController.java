package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.economy.Market;
import model.gameobject.Airship;
import view.ConstructionPanel;
import view.FightPanel;
import view.HarborPanel;
import view.MainMenuPanel;
import view.MarketPanel;
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
    private static JPanel marketPanel;
    
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
    
    public static void showFight(Airship airship){
    	if(fightPanel == null){
    		fightPanel = new FightPanel(airship);
    	}
    	showPanel(fightPanel);
    }
    
    public static void showNavigation(Airship airship){
    	if(navigationPanel == null){
    		navigationPanel = new NavigationPanel(airship);
    	}
    	showPanel(navigationPanel);
    }
    
    public static void showHarbor(Airship airship){
    	if(harborPanel == null){
    		harborPanel = new HarborPanel(airship);
    	}
    	showPanel(harborPanel);
    }
    
    
    //TODO: Market should be passed as parameter from harbor
    public static void showMarket(Airship airship)
    {
        if(marketPanel == null)
        {
            marketPanel = new MarketPanel(airship, new Market());
        }
        showPanel(marketPanel);
    }
    
    private static void showPanel(JPanel panel) {
		container.setContentPane(panel);
		container.paintAll(container.getGraphics());
	}
}
