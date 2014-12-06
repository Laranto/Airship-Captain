package model.gameobject;

import java.awt.Graphics2D;
import java.io.Serializable;

import model.economy.Money;

import common.Constants;

public class Captain  implements Renderable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Money money;
    private String name;

    public Captain() {
        this.money = new Money(Constants.CAPTAIN_START_MONEY);
        this.name = Constants.CAPTAIN_NAME_DEFAULT;
    }
    
    @Override
    public void render(Graphics2D g) {

    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
