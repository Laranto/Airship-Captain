package common;

import model.Material;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class ConstructionBrush  {

    /**
     * constructionState is the state in which the brush currently is. 0 would
     * be for placing materials, 1 would be for deleting materials on the ship
     */
    private static int constructionState = 0;
    private static Material material = null;

    public static void setMaterial(Material m) {
        ConstructionBrush.material = m;
    }

    public static Material getMaterial() {
        return ConstructionBrush.material;
    }

    public static void setConstructionState(int state) {
        ConstructionBrush.constructionState = state;
    }

    public static int getConstructionState() {
        return ConstructionBrush.constructionState;
    }


}
