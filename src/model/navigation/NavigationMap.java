package model.navigation;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.economy.Market;
import model.gameobject.Renderable;

import common.Constants;

public class NavigationMap implements Renderable{
    private Harbor currentHarbor;
    private List<Harbor> harbors;
    private BufferedImage image;
    
    public NavigationMap(Harbor currentHarbor, List<Harbor> harbors, BufferedImage image){
        this.currentHarbor = currentHarbor;
        this.harbors = harbors;
        this.image = image;
    }

    public NavigationMap(BufferedImage image) {
        currentHarbor =  new Harbor(new Point(310, 340));
        harbors = new ArrayList<Harbor>();
        harbors.add(new Harbor(new Market(), new Point(110, 500), false));
        harbors.add(new Harbor(new Market(), new Point(160, 410), false));
        harbors.add(new Harbor(new Market(), new Point(210, 300), false));
        harbors.add(new Harbor(new Market(), new Point(280, 200), false));
        harbors.add(new Harbor(new Market(), new Point(290, 60), false));
        harbors.add(new Harbor(new Market(), new Point(280, 400), false));
        harbors.add(new Harbor(new Market(), new Point(300, 490), false));
        harbors.add(new Harbor(new Market(), new Point(330, 260), false));
        harbors.add(new Harbor(new Market(), new Point(450, 200), false));
        harbors.add(new Harbor(new Market(), new Point(460, 500), false));
        harbors.add(new Harbor(new Market(), new Point(490, 290), false));
        harbors.add(new Harbor(new Market(), new Point(500, 90), false));
        harbors.add(new Harbor(new Market(), new Point(500, 400), false));
        harbors.add(new Harbor(new Market(), new Point(600, 50), false));
        harbors.add(new Harbor(new Market(), new Point(600, 270), false));
        harbors.add(new Harbor(new Market(), new Point(600, 345), false));
        harbors.add(new Harbor(new Market(), new Point(610, 200), false));
        harbors.add(new Harbor(new Market(), new Point(720, 275), false));
        harbors.add(currentHarbor);
        this.image = image;
    }

    public List<Harbor> getHarbors(){
        return harbors;
    }
    
    public Harbor getCurrentHarbor(){
        return currentHarbor;
    }
    
    public void setCurrentHarbor(Harbor harbor){
        currentHarbor.setActive(false);
        
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(
                image, 
                0,                              /*start x position*/
                0,                              /*start y position*/
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT, 
                null                            /*Image Observer*/
        );
        for(Harbor h: harbors){
            h.render(g);
        }
    }
}
