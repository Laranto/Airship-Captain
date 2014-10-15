import javax.swing.JFrame;

import controller.WindowController;


public class Start {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Airship Captain");
        frame.setSize(800,600);
        frame.setVisible(true);
        
        new WindowController(frame);
    }

}
