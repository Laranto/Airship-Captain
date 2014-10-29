/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import common.ConstructionBrush;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Material;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class ButtonController extends MouseAdapter{
    private Material materialPrototype;

    public ButtonController(Material materialPrototype) {
        this.materialPrototype = materialPrototype;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        ConstructionBrush.setMaterial(materialPrototype);
    }
    
    
}
