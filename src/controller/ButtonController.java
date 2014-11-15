package controller;

import handler.HandlerStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import common.Constants;
import common.enums.MenuItemEnum;
import common.enums.PropertyEnum;

public class ButtonController implements ActionListener {

	private JButton activeButton;
	private HandlerStrategy strategy;

	public ButtonController(HandlerStrategy strategy) {
	    this.strategy = strategy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		Object idProperty = clickedButton.getClientProperty(Constants.BUTTON_PROPERTY_ID);
		strategy.publishProperty(idProperty);
		manageButtonStyle(clickedButton, idProperty);
	}

    public void manageButtonStyle(JButton clickedButton , Object idProperty) {
        if(idProperty!=PropertyEnum.SAVE){
            if (activeButton != null) {
                activeButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            }
            if(idProperty == PropertyEnum.DELETE_MATERIAL||idProperty==PropertyEnum.DELETE_ENTITY){
    			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_DELETE_ACTIVE);
    	        }else{
    	            if(idProperty instanceof MenuItemEnum){
    	            }else{
    			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_ACTIVE);
    	            }
    		}
            activeButton = clickedButton;
        }
    }

}
