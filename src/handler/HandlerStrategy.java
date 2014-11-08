package handler;

import java.awt.event.MouseEvent;

public abstract class HandlerStrategy {

    public abstract void mouseEvent(MouseEvent e);

    public abstract void publishProperty(Object activeMaterial);
}
