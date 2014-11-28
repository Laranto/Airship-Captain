package model.gameobject;



public abstract class Material extends ShipPart {
    private static final long serialVersionUID = 1L;

    public Material(String name , int value , int weight , int durability , String imagePath) {
        super(name , value , weight , durability , imagePath);
    }
}
