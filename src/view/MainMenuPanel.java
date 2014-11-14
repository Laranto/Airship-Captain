package view;

import java.awt.GridLayout;

import javax.swing.JButton;

import controller.ExitGameListener;
import controller.LoadGameListener;
import controller.StartGameListener;

public class MainMenuPanel extends GameDefaultPanel{
	
	public MainMenuPanel(){
		createMenu();
	}
	
	private void createMenu() {
            JButton start=new JButton("Start"), load = new JButton("Load"), exit = new JButton("Exit");
            start.addActionListener(new StartGameListener());
            load.addActionListener(new LoadGameListener());
            exit.addActionListener(new ExitGameListener());
            
            setLayout(new GridLayout(3, 1));
            add(start);
            add(load);
            add(exit);
        }
}
