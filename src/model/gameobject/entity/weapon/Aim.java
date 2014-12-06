package model.gameobject.entity.weapon;

import java.awt.Graphics2D;
import java.awt.Point;

import common.Constants;

import model.gameobject.Renderable;

public class Aim implements Renderable{
    private Point from;
    private Point to;
    
    
    public Aim(Point from , Point to) {
        super();
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Constants.HARBOR_CIRCLE_BACKGROUND_ACTIVE);
        g.drawLine((int)from.getX(), (int)from.getY(), (int)to.getX(), (int)to.getY());
    }
    
}
