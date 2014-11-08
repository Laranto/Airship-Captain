package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.Material;

import common.Constants;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class ButtonController extends MouseAdapter implements ActionListener {

	private JButton activeButton;
	private Material activeMaterial;

	public ButtonController() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		BrushController.setMaterial(activeMaterial);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    
	    
		if (activeButton != null) {
			activeButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
		}
		JButton clickedButton = (JButton) e.getSource();
		activeButton = clickedButton;
		activeMaterial = (Material) clickedButton.getClientProperty("id");
		
		
		if(activeMaterial == null){
			BrushController.setConstructionState(1);
			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_DELETE_ACTIVE);
	        }else{
			BrushController.setConstructionState(0);
			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_ACTIVE);
		}
	}

}
