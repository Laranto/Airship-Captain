package model.material;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.factory.EntityFactory;
import model.gameobject.Entity;

public class EntityRenderTest extends JPanel {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(100,100);
        frame.setContentPane(new EntityRenderTest());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().repaint();
    }

    Entity mat;

    public EntityRenderTest() {
        mat = EntityFactory.getInstance().getEntities().get(0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(10, 10);
        mat.render((Graphics2D) g);
    }
}
