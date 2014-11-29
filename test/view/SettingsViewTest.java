package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingsViewTest {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        JPanel pane = new SettingsPanel();
        frame.setContentPane(pane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    

}
