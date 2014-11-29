package view;

import handler.HandlerStrategy;
import handler.MainMenuStrategy;
import handler.SettingsStrategy;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.Constants;
import common.enums.MenuItemEnum;
import controller.ButtonController;
import model.GameState;

public class SettingsPanel extends GameDefaultPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JPanel formularPanel; 

    public SettingsPanel() {
        this.setLayout(null);
        
        
        SettingsStrategy settingsStrategy = new SettingsStrategy();
        ButtonController buttonController = new ButtonController((HandlerStrategy)settingsStrategy);
       
        GridLayout mainLayout = new GridLayout(3, 1);
        mainLayout.setVgap(10);
        mainLayout.setHgap(10);
        formularPanel = new JPanel(mainLayout);
        formularPanel.setBounds(Constants.WINDOW_WIDTH/10, Constants.WINDOW_HEIGHT/10, Constants.WINDOW_WIDTH *8/10, Constants.WINDOW_HEIGHT * 3/12);
        
        /**
         * Panel Title
         */
        JLabel panelTitle = new JLabel("Einstellungen");
        panelTitle.setBounds(Constants.WINDOW_WIDTH/10, Constants.WINDOW_HEIGHT/200, Constants.WINDOW_WIDTH *8/10, Constants.WINDOW_HEIGHT * 1/12);
        panelTitle.setFont(new Font("Serif", Font.BOLD, 32));
        add(panelTitle);
        
        /**
         * Formular elements
         */
        JTextField captainName = new JTextField(GameState.getInstance().getAirship().getCaptain().getName());
        settingsStrategy.addSettingField(captainName);
        this.addInputRow(new JLabel("Captain-name: "), captainName);
        
        JTextField shipName = new JTextField(GameState.getInstance().getAirship().getName());
        settingsStrategy.addSettingField(shipName);
        this.addInputRow(new JLabel("Ship-name: "), shipName);

        
        /**
         * Formular button
         */

        JButton saveButton = new JButton("Speichern");
        saveButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        saveButton.addActionListener(buttonController);
        saveButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, MenuItemEnum.SAVE_GAME_SETTINGS);
        this.addInputRow(null, saveButton);
        
        add(formularPanel);
    }
    

    
    private void addInputRow(JComponent component, JComponent component2)
    {
        if(component != null)
        {
            component.setFont(new Font("Serif", Font.BOLD, 18));
            formularPanel.add(component);
        }else{
            formularPanel.add(new JLabel(""));
        }
        
        if(component2 != null)
        {
            component2.setFont(new Font("Serif", Font.PLAIN, 18));
            component2.setBorder(BorderFactory.createCompoundBorder( component2.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            formularPanel.add(component2);
            
            
        }else{
            formularPanel.add(new JLabel(""));
        }
        
    }
    
}
