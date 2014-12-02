import handler.Tickable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.RepaintManager;

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

        RepaintManager.currentManager(frame).setDoubleBufferingEnabled(true);
        frame.setVisible(true);
        WindowController.setFrame(frame);
        WindowController.showMainMenu();
        
        //TODO main loop
        while(true){
            try {
                Thread.sleep(Constants.GAME_TICK_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(Tickable tickable:WindowController.getTickables()){
                tickable.tick();
            }
            frame.repaint();
        }
        
        
    }

}
