package model.gameobject.material;

import java.awt.image.BufferedImage;

import model.gameobject.Material;
import model.gameobject.Tradeable;


public class Wall extends Material implements Tradeable{

    public Wall(String name , int value , int weight , int durability , BufferedImage image) {
        super(name , value , weight , durability , image);
    }
}
