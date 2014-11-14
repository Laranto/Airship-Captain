package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.factory.MaterialFactory;
import model.gameobject.Airship;

import common.Constants;

import controller.WindowController;

public class HarborViewTest {
    public static void main(String[] args) {

        MaterialFactory fac = MaterialFactory.getInstance();
        Airship testship = new Airship();
        for (int i = 0; i < 10; i++) {
            testship.placeMaterial(fac.getMaterials().get(0), 4, 4 + i);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++)
                testship.placeMaterial(fac.getMaterials().get(1), 5 + j, 4 + i);
        }
        for (int i = 0; i < 10; i++) {
            testship.placeMaterial(fac.getMaterials().get(0), 10, 4 + i);
        }

        JFrame frame = new JFrame();
        JPanel pane = new HarborPanel(testship);
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frame.setResizable(false);

        frame.setContentPane(pane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        WindowController.setFrame(frame);

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}
