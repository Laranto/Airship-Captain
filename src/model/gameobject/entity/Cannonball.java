package model.gameobject.entity;

import handler.FightStrategy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import model.GameState;
import model.gameobject.Renderable;
import model.gameobject.ShipPart;
import common.Constants;
import common.FileUtils;

public class Cannonball implements Renderable {

    private Point from;
    private Point currentPosition;
    private Point to;
    private Vector<Double> velocity;
    private BufferedImage image;
    private int damage;

    public Cannonball(Point start, int damage) {
        this.from = start;
        this.to = start;
        this.damage = damage;
        velocity = new Vector<Double>();
        velocity.add(0.0);
        velocity.add(0.0);
        try {
            this.image = FileUtils.loadImage(new File(
                    Constants.CANNONBALL_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aim(Point to) {
        this.to = to;
    }

    private void updateVelocity() {
        double length = Math.sqrt(Math.pow(velocity.get(0), 2)
                + Math.pow(velocity.get(1), 2));
        velocity.set(0, velocity.get(0) / length * damage);
        velocity.set(1, velocity.get(1) / length * damage);
    }

    public void fire() {
        velocity.set(0, (double) (to.getX() - from.getX()));
        velocity.set(1, (double) (to.getY() - from.getY()));
        currentPosition = new Point(from);
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    private boolean isFiring() {
        return currentPosition != null;
    }

    @Override
    public void render(Graphics2D g) {
        if (isFiring()) {
            g.drawImage(image, (int) currentPosition.getX(), /* start x position */
                    (int) currentPosition.getY(), /* start y position */
                    Constants.CANNONBALL_SIZE, Constants.CANNONBALL_SIZE, null); /*
                                                                                  * Image
                                                                                  * Observer
                                                                                  */
            if (removeCannonball()) {
                System.out.println("cannon removed");
                FightStrategy.removeCannonball(this);
            }
            updateVelocity();
            Point start = new Point(currentPosition);
            move();
            handleDamage(start, currentPosition);
        } else {
            int xStart = (int) getFrom().getX(), yStart = (int) getFrom()
                    .getY(), xEnd = ((int) getTo().getX()), yEnd = ((int) getTo()
                    .getY());

            g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_ACTIVE);
            g.drawLine(xStart, yStart, (xEnd), (yEnd));
        }
    }

    private void handleDamage(Point start, Point end) {
        
    }

    private boolean removeCannonball() {
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
        currentPosition.setLocation(currentPosition.getX() + velocity.get(0),
                currentPosition.getY() + velocity.get(1));
    }
}
