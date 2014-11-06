package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Airship;
import controller.WindowController;

public class MainMenuPanel extends JPanel{
	
	public MainMenuPanel(){
		createMenu();
	}
	
	private void createMenu() {
        JButton start=new JButton("Start"), load = new JButton("Load"), exit = new JButton("Exit");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                WindowController.showConstruction(new Airship());                
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
        
        
        setLayout(new GridLayout(3, 1));
        add(start);
        add(load);
        add(exit);
    }
}
