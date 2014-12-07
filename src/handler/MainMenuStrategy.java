package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import common.enums.MenuItemEnum;

import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.WindowController;

public class MainMenuStrategy extends HandlerStrategy {
    private MenuItemEnum menuItem;

    @Override
    public void mouseEvent(MouseEvent e) {
        switch(menuItem){
            case START_GAME:
                    WindowController.showSettings();
                    break;
            case LOAD_GAME:
                    new LoadGameListener().actionPerformed(null);;
                    break;
            case SETTINGS:
                    WindowController.showSettings();
                    break;
            case EXIT_GAME:
                    new ExitGameListener().actionPerformed(null);
                    break;
            default:
                    break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void publishProperty(Object menuItem) {
        if(menuItem instanceof MenuItemEnum){
            this.menuItem = (MenuItemEnum)menuItem;
            mouseEvent(null);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
