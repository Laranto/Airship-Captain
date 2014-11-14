package handler;

import java.awt.event.MouseEvent;

import model.Airship;
import model.enums.MARKET_BUTTONS;
import model.enums.MENU;
import controller.WindowController;

public class MarketStrategy extends HandlerStrategy {

    private MARKET_BUTTONS activeMenu;
    private Airship airship;

    public MarketStrategy(Airship airship) {
        this.airship = airship;
    }

    @Override
    public void mouseEvent(MouseEvent e) {
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
    }

    @Override
    public void publishProperty(Object activeMenu) {
        this.activeMenu = (MARKET_BUTTONS) activeMenu;
        this.mouseEvent(null);
    }

}
