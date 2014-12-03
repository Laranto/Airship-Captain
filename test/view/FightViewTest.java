package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameState;
import model.gameobject.Airship;
import model.navigation.FightScenario;
import common.Constants;
import common.FileUtils;

public class FightViewTest {
    public static void main(String[] args) {

        GameState.getInstance().setAirship((Airship) FileUtils.loadObjectFile(Constants.FOLDER_AIRSHIPS, "hashership", Constants.FILE_ENDING_SHIP));
        
        JFrame frame = new JFrame();
        JPanel pane = new FightPanel(new FightScenario("Dies ist ein Fight-Scenario", GameState.getInstance().getAirship().getCaptain()));
        frame.setContentPane(pane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
