package model.material;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import factory.MaterialFactory;

import model.Material;

public class WallRenderTest extends JPanel {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(100,100);
        frame.setContentPane(new WallRenderTest());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().repaint();
    }

    Material mat;

    public WallRenderTest() {
        mat = MaterialFactory.getInstance().getMaterials().get(0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(10, 10);
        mat.render((Graphics2D) g);
    }
}
