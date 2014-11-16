package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.gameobject.Airship;
import common.enums.MenuItemEnum;
import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.WindowController;

public class MainMenuStrategy extends HandlerStrategy {
    private MenuItemEnum menuItem;
    private Airship airship;

    public MainMenuStrategy(Airship airship) {
        this.airship = airship;
    }

    @Override
    public void mouseEvent(MouseEvent e) {
        switch(menuItem){
            case START_GAME:
                    WindowController.showConstruction(airship);
                    break;
            case LOAD_GAME:
                    new LoadGameListener().actionPerformed(null);
                    break;
            case SETTINGS:
                    //TODO: implement settings
                    break;
            case EXIT_GAME:
                    new ExitGameListener().actionPerformed(null);
                    break;
            default:
                    break;
        }
    }

    @Override
    public void keyEvent(KeyEvent e) {
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

}
