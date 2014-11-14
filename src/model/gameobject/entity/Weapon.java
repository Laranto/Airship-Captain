package model.gameobject.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import model.gameobject.Entity;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class Weapon extends Entity{
    private int damage;
    private int ammunition;
    private Point aim;

    public Weapon(String name , int value , int weight , int durability , BufferedImage image , Vector<Integer> orientation , Dimension2D size) {
        super(name , value , weight , durability , image , orientation , size);
    }

    public void aim(int tileX, int tileY){
        aim = new Point(tileX, tileY);
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
