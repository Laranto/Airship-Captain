package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

/**
 *  State-Machine for processing inputs and calling model functions. Serves as facade-layer from the view to the model 
 */
public abstract class HandlerStrategy extends Observable {

    public abstract void mouseEvent(MouseEvent e);
    public abstract void keyReleased(KeyEvent e);

    public abstract void publishProperty(Object activeMaterial);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void keyTyped(KeyEvent e);
    public abstract void keyPressed(KeyEvent e);
}
