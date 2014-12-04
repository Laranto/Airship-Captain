package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;

public abstract class HandlerStrategy extends Observable {

    public abstract void mouseEvent(MouseEvent e);
    public abstract void keyEvent(KeyEvent e);

    public abstract void publishProperty(Object activeMaterial);
    public abstract void mouseMoved(MouseEvent e);
}
