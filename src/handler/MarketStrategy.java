package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.economy.Market;
import model.gameobject.Airship;

import common.enums.MenuItemEnum;

import controller.WindowController;

public class MarketStrategy extends HandlerStrategy {

    private MenuItemEnum activeMenu;
    private Airship airship;
    private Market market;

    public MarketStrategy(Airship airship, Market market) {
        this.airship = airship;
        this.market = market;
    }

    @Override
    public void mouseEvent(MouseEvent e) {
 
        
        if(this.activeMenu != null)
        {
            switch (this.activeMenu) {
            case BUY:
                // TODO set market mode to buy (like the brush constructer)
                break;
            case SELL:
                // TODO set market mode to sell
                break;
            case HARBOR:
                WindowController.showHarbor(this.airship);
                break;
            default:
                break;
            }
            
            this.activeMenu = null;
        }
    }

    @Override
    public void publishProperty(Object activeMenu) {
        this.activeMenu = (MenuItemEnum) activeMenu;
        this.mouseEvent(null);
    }

    @Override
    public void keyEvent(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
