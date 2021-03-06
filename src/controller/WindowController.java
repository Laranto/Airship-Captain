package controller;

import handler.Tickable;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GameState;
import model.navigation.FightScenario;
import model.navigation.TradeScenario;
import view.ConstructionPanel;
import view.FightPanel;
import view.HarborPanel;
import view.MainMenuPanel;
import view.MarketPanel;
import view.NavigationPanel;
import view.SettingsPanel;
import view.TradeScenarioPanel;

import common.Constants;

/**
 * Controls which panel is being displayed.
 */
public class WindowController {

    private static List<Tickable> tickables = new LinkedList<Tickable>();
    private static JFrame container;
    private static JPanel mainMenu;
    private static JPanel constructionPanel;
    private static JPanel navigationPanel;
    private static JPanel harborPanel;
    private static JPanel marketPanel;
    private static JPanel settingsPanel;

    public static void setFrame(JFrame frame) {
        container = frame;
    }

    public static void showMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenuPanel();
        }
        showPanel(mainMenu);
    }

    public static void showConstruction() {
        if (constructionPanel == null) {
            constructionPanel = new ConstructionPanel();
        }
        showPanel(constructionPanel);
        constructionPanel.requestFocus();
    }

    public static void showNavigation() {
        if (navigationPanel == null) {
            navigationPanel = new NavigationPanel();
        }
        showPanel(navigationPanel);
    }

    public static void showHarbor() {
        if (harborPanel == null) {
            if (GameState.getInstance().getCurrentHarbor() == null) {
                GameState.getInstance().setCurrentHarbor(
                        Constants.HARBORS.get(0));
            }
            harborPanel = new HarborPanel();
        }
        showPanel(harborPanel);
    }

    public static void showMarket() {
        if (marketPanel == null) {
            marketPanel = new MarketPanel();
        }
        showPanel(marketPanel);
    }

    public static void showSettings() {
        if (settingsPanel == null) {
            settingsPanel = new SettingsPanel();
        }
        showPanel(settingsPanel);
    }
    
    public static void showTradeScenarioPanel(TradeScenario scenario) {
        showPanel(new TradeScenarioPanel(scenario));
    }
    
    public static void showFightScenarioPanel(FightScenario scenario) {
        showPanel(new FightPanel(scenario));
    }

    public static void showPanel(JPanel panel) {
        container.setContentPane(panel);
        container.paintAll(container.getGraphics());
    }

    public static void showError(String title, String message) {
        JOptionPane.showMessageDialog(container, message, title,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(container, message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showTravelConfirmation(String title, String message) {
        Object[] options = { "Abbrechen", "Segel setzen!" };
        int n = JOptionPane.showOptionDialog(container, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);

        return n;
    }

    public static String showFileChooser(String defaultFolder,
            String fileExtensionDescription, String fileExtension,
            boolean isSave) {
        JFileChooser chooser = new JFileChooser(new File(defaultFolder));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                fileExtensionDescription, fileExtension);
        chooser.setFileFilter(filter);

        int returnVal;
        if (isSave) {
            returnVal = chooser.showSaveDialog(container);
        } else {
            returnVal = chooser.showOpenDialog(container);
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getName();
        }
        return null;
    }

    public static List<Tickable> getTickables() {
        return tickables;
    }
}
