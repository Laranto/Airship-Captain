package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  Controls which panel is being displayed.
 */
public class WindowController {

    
    private JFrame frame;
    
    private JPanel menu, constructionView;
    
    
    public WindowController(JFrame frame) {
        this.frame=frame;
        menu=createMenu();
        setMenu();
    }
    
    private JPanel createMenu() {
        JPanel menuPane = new JPanel();
        JButton start=new JButton("Start"), load = new JButton("Load"), exit = new JButton("Exit");
        start.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub                
            }
        });
        load.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        
        
        menuPane.setLayout(new GridLayout(3, 1));
        menuPane.add(start);
        menuPane.add(load);
        menuPane.add(exit);
        
        return menuPane;
    }

    public void setMenu(){
        frame.setContentPane(menu);
    }
    
    

    public void repaint() {
        frame.repaint();
    }

}
