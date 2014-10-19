package view;

import javax.swing.JFrame;

import factory.MaterialFactory;

import model.Airship;
import model.Material;

public class ConstructionViewTest {
    public static void main(String[] args) {
        MaterialFactory fac = MaterialFactory.getInstance();
        Airship testship = new Airship();
        for(int i = 0;i<10;i++){
            testship.placeMaterial(fac.getMaterials().get(0), 4, 4+i);
        }
        for(int i = 0;i<10;i++){
            for(int j = 0;j<5;j++)
            testship.placeMaterial(fac.getMaterials().get(1), 5+j, 4+i);
        }
        for(int i = 0;i<10;i++){
            testship.placeMaterial(fac.getMaterials().get(0), 10, 4+i);
        }
        JFrame frame = new JFrame();
        frame.setContentPane(new ConstructionView(testship));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
