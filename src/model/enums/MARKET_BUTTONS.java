package model.enums;

public enum MARKET_BUTTONS {

        BUY("Kaufen"), 
        SELL("Verkaufen"),
        HARBOR("Hafen");
        
        private String text;

        private MARKET_BUTTONS(String text) {
            this.text = text;
        }
        
        public String text()
        {
            return this.text;
        }
}
