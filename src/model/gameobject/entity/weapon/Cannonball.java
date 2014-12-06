package model.gameobject.entity.weapon;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.gameobject.Airship;
import model.gameobject.Renderable;
import model.gameobject.ShipPart;

import common.Constants;
import common.FileUtils;
import common.Math.Vector2d;

public class Cannonball implements Renderable {

    private Point from;
    private Point to;
    private Point currentPosition;
    private Vector2d direction;
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
        direction = new Vector2d(to.getX()-from.getX(), to.getY()-from.getY());
        direction = direction.getNormalizedVector();
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


    public boolean isOutOfBounds() {
        return currentPosition.getX() < 0 || currentPosition.getX() > Constants.WINDOW_WIDTH || 
                currentPosition.getY() < 0 || currentPosition.getY() > Constants.WINDOW_HEIGHT;
    }

    public void calculateCollision(ShipPart hitShipPart) {
        if(hitShipPart!=null){
            if (damage > hitShipPart.getDurability()) {
                damage = damage - hitShipPart.getDurability();
                hitShipPart.setDurability(0);
            } else {
                damage = 0;
                hitShipPart.setDurability(hitShipPart.getDurability() - damage);
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void move(Airship... airships) {
        // TODO detect all tiles between current and next current position
//        updateVelocity();
        int remainingMove = damage;
        while(remainingMove>radius&&!isOutOfBounds()){
            Vector2d move = new Vector2d(direction).multiply(radius);
            currentPosition.setLocation(currentPosition.getX() + move.getX(),
                    currentPosition.getY() + move.getY());
            checkCollision(airships);
            remainingMove=(int) (remainingMove-radius);
        }
        if(remainingMove!=0){
            Vector2d move = new Vector2d(direction).multiply(remainingMove);
            currentPosition.setLocation(currentPosition.getX() + move.getX(),
                    currentPosition.getY() + move.getY());
            checkCollision(airships);
        }
    }

    private void checkCollision(Airship... airships) {
        if(!isOutOfBounds()){
            int x = (int)(currentPosition.getX()+radius)/Constants.TILE_SIZE;
            int yTop=(int)(currentPosition.getY())/Constants.TILE_SIZE;
            int yBot=(int)(currentPosition.getY()+2*radius)/Constants.TILE_SIZE;
            int airship = 0;
            if(x>Constants.AIRSHIP_WIDTH_TILES){
                airship=1;
            }
            if(yBot==yTop){
                calculateCollision(airships[airship].getShipPartByPosition(x%Constants.AIRSHIP_WIDTH_TILES, yTop));
            }else{
                calculateCollision(airships[airship].getShipPartByPosition(x%Constants.AIRSHIP_WIDTH_TILES, yTop));
                if(damage>0){
                    calculateCollision(airships[airship].getShipPartByPosition(x%Constants.AIRSHIP_WIDTH_TILES, yBot));
                }
            }
            airships[airship].checkDamage();
        }
    }
}
