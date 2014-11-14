package common.enums;

public enum MenuItemEnum {

    /*
     * Market
     */
    BUY("Kaufen"), 
    SELL("Verkaufen"),
    HARBOR("Hafen"),
    /*
     * Harbor
     */
	SHIPYARD("Werft"), 
	MARKET("Markt"), 
	SAVE_GAME("Speichern..."), 
	LOAD_GAME("Laden..."), 
	EXIT_GAME("Spiel verlassen") ;
	
	private String text;

	private MenuItemEnum(String text) {
		this.text = text;
	}
	
	public String text()
	{
		return this.text;
	}
}
