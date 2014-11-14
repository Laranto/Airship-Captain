/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.factory;

import model.gameobject.ShipPart;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public abstract class ShippartFactory<E extends ShipPart> {
    
    /**
     * Creates a duplicate of the prototype. Must be something that the Factory creates.
     * @param prototype
     */
    public abstract E instanzise(E prototype);
    public abstract void parse();
}
