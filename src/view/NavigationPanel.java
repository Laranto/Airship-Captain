package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import model.gameobject.Airship;
import model.navigation.NavigationMap;

import common.Constants;
import common.ImageLoader;


public class NavigationPanel extends GameDefaultPanel {

    private Airship airship;
    private NavigationMap navigationMap;
    
    public NavigationPanel(Airship airship){
        this.airship = airship;
        try {
            this.navigationMap = new NavigationMap(ImageLoader.loadImage(new File(Constants.MAP)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        navigationMap.render(g2);
    }
}
