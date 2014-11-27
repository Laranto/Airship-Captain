package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import common.enums.MenuItemEnum;

import controller.WindowController;

public class MarketStrategy extends HandlerStrategy {

    private MenuItemEnum activeMenu;

    public MarketStrategy() {
    }

    @Override
    public void mouseEvent(MouseEvent e) {
 
        
        if(this.activeMenu != null)
        {
            switch (this.activeMenu) {
            case HARBOR:
                WindowController.showHarbor();
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
    public void keyEvent(KeyEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
