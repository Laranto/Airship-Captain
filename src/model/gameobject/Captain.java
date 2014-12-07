package model.gameobject;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import model.GameState;
import model.economy.Money;

import common.Constants;
import common.FileUtils;

public class Captain  implements Renderable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private Money money;
    private String name;
    private Point currentPosition;
    private BufferedImage image;

    public Captain() {
        this.money = new Money(Constants.CAPTAIN_START_MONEY);
        this.name = Constants.CAPTAIN_NAME_DEFAULT;
        try {
            this.image = FileUtils.loadImage(new File(Constants.CAPTAIN_HAT_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(
                image, 
                (int)currentPosition.getX(),                         /*start x position*/
                (int)currentPosition.getY(),                         /*start y position*/
                image.getWidth(),
                image.getHeight(), 
                null                                                 /*Image Observer*/
        );
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
    
    public void setCurrentPosition(Point currentPosition){
        this.currentPosition = currentPosition;
    }

    public void move(KeyEvent e) {
        switch(e.getKeyCode()){
        case Constants.MOVE_UP:
            if(isMovedAllowed((int)currentPosition.getX(), (int)(currentPosition.getY()+Constants.CAPTAIN_MOVE_SPEED))){                
                currentPosition.setLocation(currentPosition.getX(), currentPosition.getY()+Constants.CAPTAIN_MOVE_SPEED);
            }
            break;
        case Constants.MOVE_DOWN:
            if(isMovedAllowed((int)currentPosition.getX(), (int)(currentPosition.getY()-Constants.CAPTAIN_MOVE_SPEED))){ 
                currentPosition.setLocation(currentPosition.getX(), currentPosition.getY()-Constants.CAPTAIN_MOVE_SPEED);
            }
            break;
        case Constants.MOVE_LEFT:
            if(isMovedAllowed((int)(currentPosition.getX()-Constants.CAPTAIN_MOVE_SPEED), (int)(currentPosition.getY()))){ 
                currentPosition.setLocation(currentPosition.getX()-Constants.CAPTAIN_MOVE_SPEED, currentPosition.getY());
            }
            break;
        case Constants.MOVE_RIGHT:
            if(isMovedAllowed((int)(currentPosition.getX()+Constants.CAPTAIN_MOVE_SPEED), (int)(currentPosition.getY()))){ 
                currentPosition.setLocation(currentPosition.getX()+Constants.CAPTAIN_MOVE_SPEED, currentPosition.getY());
            }
            break;
        }
    }

    private boolean isMovedAllowed(int tileX, int tileY) {
        return !GameState.getInstance().getAirship().getMaterialByPosition(tileX, tileY).isShattered();
    }

}
