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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        WindowController.setFrame(frame);
        WindowController.showMainMenu();
        //TODO main loop
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
