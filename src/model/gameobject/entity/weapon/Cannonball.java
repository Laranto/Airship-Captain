package model.gameobject.entity.weapon;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.gameobject.Renderable;
import model.gameobject.ShipPart;

import common.Constants;
import common.FileUtils;
import common.Math.Vector2d;

public class Cannonball implements Renderable {

    private Point from;
    private Point to;
    private Point currentPosition;
    private Vector2d velocity;
    private BufferedImage image;
    private int damage;
    private double radius;
    
    
    
    public Cannonball(Aim aim , int damage) {
        radius=Constants.CANNONBALL_SIZE/2;
        
        try {
            this.image = FileUtils.loadImage(new File(
                    Constants.CANNONBALL_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        from = new Point(aim.getFrom());
        to = new Point(aim.getTo());
        this.damage=damage;
        velocity = new Vector2d(to.getX()-from.getX(), to.getY()-from.getY());
        velocity = velocity.getNormalizedVector();
        velocity.multiply(damage);
        currentPosition = new Point((int)from.getX(),(int)(from.getY()-radius));        
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }


    @Override
    public void render(Graphics2D g) {
            g.drawImage(image, (int) currentPosition.getX(), /* start x position */
                    (int) currentPosition.getY(), /* start y position */
                    Constants.CANNONBALL_SIZE, Constants.CANNONBALL_SIZE, null); 
    }

    private void handleDamage(Point start, Point end) {
        
    }

    public boolean isOutOfBounds() {
        return currentPosition.getX() < 0 || currentPosition.getX() > Constants.WINDOW_WIDTH || 
                currentPosition.getY() < 0 || currentPosition.getY() > Constants.WINDOW_HEIGHT;
    }

    public void calculateCollision(ShipPart hitShipPart) {
        if (damage > hitShipPart.getDurability()) {
            damage = damage - hitShipPart.getDurability();
        } else {
            damage = 0;
            hitShipPart.setDurability(hitShipPart.getDurability() - damage);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void move() {
        // TODO detect all tiles between current and next current position
        if (isOutOfBounds()) {
            System.out.println("cannon removed");
        }
//        updateVelocity();
        currentPosition.setLocation(currentPosition.getX() + velocity.getX(),
                currentPosition.getY() + velocity.getY());
//        move();
//        handleDamage(start, currentPosition);
    }
}
