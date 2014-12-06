package model.gameobject.entity;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import model.gameobject.Entity;
import model.gameobject.entity.weapon.Aim;
import model.gameobject.entity.weapon.Cannonball;

import common.Constants;

public class Weapon extends Entity {
    private static final long serialVersionUID = 1L;
    private int damage;
    private int ammunition;
    private Aim aim;

    public Weapon(String name , int value , int weight , int durability , String imagePath , Vector<Integer> orientation , Dimension size, int damage) {
        super(name , value , weight , durability , imagePath , orientation , size);
        this.damage = damage;
    }

    public Aim aim(int x, int y){
        aim = new Aim(
                new Point(
                (int)(getPosition().getX()+getSize().getWidth())*Constants.TILE_SIZE, 
                (int)((getPosition().getY()+getSize().getHeight()/2)*Constants.TILE_SIZE)),
                new Point(x,y));
        return aim;
    };
    public Cannonball fire(){
        Cannonball firedCannonball = null;
        if(aim!=null){
            firedCannonball = new Cannonball(aim,getDamage());
            aim = null;
        }
        return firedCannonball;
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
