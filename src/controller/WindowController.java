package controller;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.Constants;
import model.GameState;
import model.economy.Market;
import model.navigation.Harbor;
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
    
    public static void showConstruction(){
    	if(constructionPanel == null){
    		constructionPanel = new ConstructionPanel();
    	}
    	showPanel(constructionPanel);
    	constructionPanel.requestFocus();
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
                if(GameState.getInstance().getCurrentHarbor() == null){
                    GameState.getInstance().setCurrentHarbor(new Harbor(new Market(), new Point(310, 340), true));
                }
    		harborPanel = new HarborPanel();
    	}
    	showPanel(harborPanel);
    }
    
    
    //TODO: Market should be passed as parameter from harbor
    public static void showMarket()
    {
        if(marketPanel == null)
        {
            marketPanel = new MarketPanel();
        }
        showPanel(marketPanel);
    }
    
    private static void showPanel(JPanel panel) {
		container.setContentPane(panel);
		container.paintAll(container.getGraphics());
	}

    public static void showError(String title,String message) {
        JOptionPane.showMessageDialog(container, message, title,JOptionPane.ERROR_MESSAGE);
    }
    
    public static int showTravelConfirmation(String title, String message)
    {
        Object[] options = {"Abbrechen", "Segel setzen!"};
        int n = JOptionPane.showOptionDialog(container,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,    
                options,  
                options[0]);
        
        return n;
    }
    
    
    public static String showFileChooser()
    {
        JFileChooser chooser = new JFileChooser(Constants.FOLDER_GAME_DATA);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Airship Captain Datei", Constants.FILE_ENDNG_GAME);
        chooser.setFileFilter(filter);
    
        int returnVal = chooser.showOpenDialog(container);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           return chooser.getSelectedFile().getName();
        }
        return null;
    }
    
}
