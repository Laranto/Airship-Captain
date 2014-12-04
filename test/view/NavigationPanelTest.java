package view;

import javax.swing.JFrame;

import model.GameState;

import org.junit.Test;

import common.Constants;
import controller.WindowController;

public class NavigationPanelTest {
    
    @Test
    public void fulltest() {
        JFrame frame = new JFrame("Airship Captain");
        frame.setSize(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        GameState.getInstance().setCurrentHarbor(Constants.HARBORS.get(0));
        WindowController.setFrame(frame);
        WindowController.showNavigation();
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

}
