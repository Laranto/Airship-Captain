package model.gameobject;

import java.awt.Graphics2D;
import java.io.Serializable;

import model.economy.Money;

public class Captain implements Renderable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Money money;

    public Captain() {
        this.money = new Money(1000);
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

}
