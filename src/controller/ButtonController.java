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

	private static JButton previousSelected;
	private Material materialPrototype;

	public ButtonController() {
	}

	public ButtonController(Material materialPrototype) {
		this.materialPrototype = materialPrototype;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		BrushController.setMaterial(materialPrototype);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (previousSelected != null) {
			previousSelected
					.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
		}
		JButton clickedButton = (JButton) e.getSource();
		previousSelected = clickedButton;
		String command = String.valueOf(clickedButton.getClientProperty("id"));

		switch (command) {
		case "removeMaterial":
			BrushController.setConstructionState(1);
			clickedButton
					.setBackground(Constants.BUTTON_BACKGROUND_DELETE_ACTIVE);
			break;

		case "placeMaterial":
			BrushController.setConstructionState(0);
			clickedButton.setBackground(Constants.BUTTON_BACKGROUND_ACTIVE);
			break;
		}
	}

}
