package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class HandlerStrategy {

    public abstract void mouseEvent(MouseEvent e);
    public abstract void keyEvent(KeyEvent e);

    public abstract void publishProperty(Object activeMaterial);
    
}
