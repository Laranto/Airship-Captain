package view;

import java.awt.Graphics2D;

/**
 *  To be implemented by everything that should be displayed by a graphics 2d component.
 */
public interface Renderable {
    
    /**
     * Draws a graphical representation of the object
     */
    public void render(Graphics2D g);
}
