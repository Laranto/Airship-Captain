package controller;

import handler.HandlerStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import common.Constants;
import controller.enumeration.PropertyEnum;

public class ButtonController implements ActionListener {

	private JButton activeButton;
	private HandlerStrategy strategy;

	public ButtonController(HandlerStrategy strategy) {
	    this.strategy = strategy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (activeButton != null) {
			activeButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
		}
		JButton clickedButton = (JButton) e.getSource();
		activeButton = clickedButton;
		Object idProperty = clickedButton.getClientProperty(Constants.BUTTON_PROPERTY_ID);
		strategy.publishProperty(idProperty);
		if(idProperty == PropertyEnum.DELETE_MATERIAL){
			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_DELETE_ACTIVE);
	        }else{
			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_ACTIVE);
		}
	}

}
