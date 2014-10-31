import javax.swing.JFrame;

import common.Constants;

import controller.WindowController;


public class Start {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Airship Captain");
        frame.setSize(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        new WindowController(frame);
    }

}
