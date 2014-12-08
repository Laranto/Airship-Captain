import javax.swing.JFrame;
import javax.swing.RepaintManager;

import common.Constants;

import controller.WindowController;

/**
 * The starting class for the project Airship-Captain.
 * 
 */
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

        RepaintManager.currentManager(frame).setDoubleBufferingEnabled(true);
        frame.setVisible(true);
        WindowController.setFrame(frame);
        WindowController.showMainMenu();
        
        while(true){
            try {
                Thread.sleep(Constants.GAME_TICK_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0;i<WindowController.getTickables().size();i++){
                WindowController.getTickables().get(i).tick();
            }
            frame.repaint();
        }
        
        
    }

}
