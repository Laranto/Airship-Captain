package common.Math;

/**
 * Simple 2d vector class
 */
public class Vector2d {
    double x,y;

    /**
     * Creates a vector using the given values
     * @param x
     * @param y
     */
    public Vector2d(double x , double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector using the same values as the given vector has
     * @param vector
     */
    public Vector2d(Vector2d vector) {
        this(vector.getX(),vector.getY());
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
    
    /**
     * Calculates the length of the vector
     */
    public double getLength(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    /**
     * Normalizes the vector
     * @return the normalized vector
     */
    public Vector2d getNormalizedVector(){
        double length=getLength();
        return new Vector2d(x/length, y/length);
    }
    
    /**
     * performs a scalar addition
     * @param n the number to be added to the vector
     * @return this vector  
     */
    public Vector2d add(double n){
        x=x+n;
        y=y+n;
        return this;
    }
    
    /**
     * performs a vector addition
     * @param v the vector to be added to the vector
     * @return this vector  
     */
    public Vector2d add(Vector2d v){
        x=x+v.getX();
        y=y+v.getY();
        return this;
    }
    
    /**
     * @param n the scalar to multiply the vector with
     * @return this vector
     */
    public Vector2d multiply(double n){
        x=x*n;
        y=y*n;
        return this;
    }
}
