package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import common.enums.MenuItemEnum;

import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.SaveGameListener;
import controller.WindowController;

/**
 * When the player is on the harborview than this class
 * gets the input of the player and handles it
 *
 */
public class HarborStrategy extends HandlerStrategy {

	private MenuItemEnum activeMenu;

	@Override
	public void mouseEvent(MouseEvent e) {
	   
		switch (this.activeMenu) {
		case SHIPYARD:
			WindowController.showConstruction();
			break;
		case MARKET:
		    WindowController.showMarket();
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
	     case SETTINGS:
	         WindowController.showSettings();
	        break;
		case NAVIGATION_MAP:
		        WindowController.showNavigation();
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
