/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.Material;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class ButtonController extends MouseAdapter implements ActionListener {

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
        JButton clickedButton = (JButton) e.getSource();
        String command = String.valueOf(clickedButton.getClientProperty("id"));

        switch (command) {
            case "removeMaterial":
                BrushController.setConstructionState(1);
                break;

            case "placeMaterial":
                BrushController.setConstructionState(0);
                break;
        }
    }

}
