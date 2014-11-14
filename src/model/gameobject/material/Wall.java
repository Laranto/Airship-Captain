package model.gameobject.material;

import java.awt.image.BufferedImage;

import model.gameobject.Material;


public class Wall extends Material{

    public Wall(String name , int value , int weight , int durability , BufferedImage image) {
        super(name , value , weight , durability , image);
    }
}
