package controller;

import model.Material;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class ConstructionBrush {
    
    public static Material material = null;
    
    
    public static void setMaterial(Material m)
    {
        ConstructionBrush.material = m;
    }
    
    public static Material getMaterial()
    {
        return ConstructionBrush.material;
    }
    
}
