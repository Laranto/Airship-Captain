package model;

public abstract class ShipPart extends GameObject {
    /**
     * The Shipparts health
     */
    private double durability;

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }
    
    
    
}
