package model.entity;

import java.awt.Graphics2D;
import model.Entity;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class Weapon extends Entity{
    private int damage;
    private int ammunition;

    @Override
    public void render(Graphics2D g) {
    }
    
    public void aim(){
    
    };
    public void fire(){
    
    };
    public void reload(){
    
    };

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the ammunition
     */
    public int getAmmunition() {
        return ammunition;
    }

    /**
     * @param ammunition the ammunition to set
     */
    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }
    
}
