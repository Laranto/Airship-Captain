package model.gameobject;

import java.awt.image.BufferedImage;


public abstract class Material extends ShipPart {
    public Material(String name , int value , int weight , int durability , BufferedImage image) {
        super(name , value , weight , durability , image);
    }
}
