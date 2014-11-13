package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import common.Constants;

public abstract class GameDefaultPanel extends JPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public GameDefaultPanel() {
        
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
    }
    

}
