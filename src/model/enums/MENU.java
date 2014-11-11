package model.enums;

public enum MENU {

	SHIPYARD("Werft"), 
	MARKET("Markt"), 
	SAVE_GAME("Speichern..."), 
	LOAD_GAME("Laden..."), 
	EXIT_GAME("Spiel verlassen") ;
	
	private String text;

	private MENU(String text) {
		this.text = text;
	}
	
	public String text()
	{
		return this.text;
	}
}
