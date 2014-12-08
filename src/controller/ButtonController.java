package controller;

import handler.HandlerStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import common.Constants;
import common.enums.MenuItemEnum;
import common.enums.PropertyEnum;

/**
 * This class is used by buttons on the panels.
 * It directs the click of the button to the according strategy.
 * 
 */
public class ButtonController implements ActionListener {

    private JButton activeButton;
    private HandlerStrategy strategy;

    public ButtonController(HandlerStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sends the client property of the pressed button to 
     * the given strategy.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        Object idProperty = clickedButton
                .getClientProperty(Constants.BUTTON_PROPERTY_ID);
        strategy.publishProperty(idProperty);
        manageButtonStyle(clickedButton, idProperty);
    }

    /**
     * Handles the coloring of the given button according to its property.
     * @param clickedButton
     * @param idProperty
     */
    public void manageButtonStyle(JButton clickedButton, Object idProperty) {
        if (idProperty != PropertyEnum.SAVE) {
            if (activeButton != null) {
                activeButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            }
            if (idProperty == PropertyEnum.DELETE_MATERIAL || idProperty == PropertyEnum.DELETE_ENTITY) {
                clickedButton.setBackground(Constants.BUTTON_BACKGROUND_DELETE_ACTIVE);
            } else {
                if (idProperty instanceof MenuItemEnum) {
                } else {
                    clickedButton.setBackground(Constants.BUTTON_BACKGROUND_ACTIVE);
                }
            }
            activeButton = clickedButton;
        }
    }

}
