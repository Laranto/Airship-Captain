package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import common.enums.MenuItemEnum;

import controller.WindowController;

/**
 * handles the input of the marketpanl
 */
public class MarketStrategy extends HandlerStrategy {

    private MenuItemEnum activeMenu;
    
    @Override
    public void mouseEvent(MouseEvent e) {
 
        
        if(this.activeMenu != null)
        {
            switch (this.activeMenu) {
            case HARBOR:
                WindowController.showHarbor();
                break;
            case NAVIGATION_MAP:
                WindowController.showNavigation();
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void publishProperty(Object activeMenu) {
        this.activeMenu = (MenuItemEnum) activeMenu;
        this.mouseEvent(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
