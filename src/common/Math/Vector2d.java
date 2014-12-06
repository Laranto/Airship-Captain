package common.Math;

/**
 * Simple 2d vector class
 * @author Arni
 *
 */
public class Vector2d {
    double x,y;

    public Vector2d(double x , double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public double getLength(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public Vector2d getNormalizedVector(){
        double length=getLength();
        return new Vector2d(x/length, y/length);
    }
    
    public void add(double n){
        x=x+n;
        y=y+n;
    }
    public void multiply(double n){
        x=x*n;
        y=y*n;
    }
}
