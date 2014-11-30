package common.enums;

public enum MenuItemEnum {
    
    START_GAME("Spiel starten"),
    SETTINGS("Optionen..."),

    /*
     * Market
     */
    BUY("Kaufen"), 
    SELL("Verkaufen"),
    MARKET_ITEM("Market Item"),
    SHIP_ITEM("Ship Item"),
    
    HARBOR("Hafen"),
    /*
     * Harbor
     */
	SHIPYARD("Werft"), 
	MARKET("Markt"), 
	SAVE_GAME("Speichern..."), 
	LOAD_GAME("Laden..."), 
	EXIT_GAME("Spiel verlassen"),
	
	NAVIGATION_MAP("Karte"),
	
	/*
	 * Fight
	 */
	EXIT_FIGHT("Aus Kampf fliehen.."),
	ROTATE_SHIP("Schiff wenden"),
	
	/*
	 * Settings
	 */
	SAVE_GAME_SETTINGS("Einstellungen speichern"),
	
	
	
	;
	
	private String text;

	private MenuItemEnum(String text) {
		this.text = text;
	}
	
	public String text()
	{
		return this.text;
	}
}
