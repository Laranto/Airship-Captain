package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import common.enums.MenuItemEnum;
import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.SaveGameListener;
import controller.WindowController;
import model.GameState;
import model.gameobject.Airship;

public class HarborStrategy extends HandlerStrategy {

	private MenuItemEnum activeMenu;

	private Airship airship;

	public HarborStrategy(Airship airship) {
		this.airship = airship;
	}

	@Override
	public void mouseEvent(MouseEvent e) {
	   
		switch (this.activeMenu) {
		case SHIPYARD:
			WindowController.showConstruction();
			break;
		case MARKET:
		    WindowController.showMarket(GameState.getInstance().getAirship());
			break;
		case SAVE_GAME:
		    new SaveGameListener().actionPerformed(null);
			break;
		case LOAD_GAME:
			new LoadGameListener().actionPerformed(null);
			break;
		case EXIT_GAME:
			new ExitGameListener().actionPerformed(null);
			break;
		case NAVIGATION_MAP:
		        WindowController.showNavigation(airship);
		        break;
		default:
			break;
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
