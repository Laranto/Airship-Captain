package model.material;

import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class Floor extends model.Material{

    @Override
    public void render(Graphics2D g) {
        g.drawImage(getImage(), null, 0, 0);
    }

    
}
