package model.gameobject;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int value;
    private int weight;
    
    
    
    
    public GameObject(String name , int value , int weight) {
        super();
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
}