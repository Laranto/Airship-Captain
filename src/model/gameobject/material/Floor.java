package model.gameobject.material;

import model.gameobject.Material;


public class Floor extends Material{
    private static final long serialVersionUID = 1L;

    public Floor(String name , int value , int weight , int durability , String imagePath) {
        super(name , value , weight , durability , imagePath);
    }
    
}
