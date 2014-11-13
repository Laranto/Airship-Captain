package handler;

import java.awt.event.MouseEvent;

import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.WindowController;
import model.Airship;
import model.enums.MENU;

public class HarborStrategy extends HandlerStrategy {

	private MENU activeMenu;

	private Airship airship;

	public HarborStrategy(Airship airship) {
		this.airship = airship;
	}

	@Override
	public void mouseEvent(MouseEvent e) {
		switch (this.activeMenu) {
		case SHIPYARD:
			WindowController.showConstruction(this.airship);
			break;
		case MARKET:
		    WindowController.showMarket(this.airship);
			break;
		case SAVE_GAME:
			// TODO: implement save listener
			break;
		case LOAD_GAME:
			new LoadGameListener().actionPerformed(null);
			break;
		case EXIT_GAME:
			new ExitGameListener().actionPerformed(null);
			break;

		default:
			break;
		}
	}

	@Override
	public void publishProperty(Object activeMenu) {
		this.activeMenu = (MENU) activeMenu;
		this.mouseEvent(null);
	}

}
