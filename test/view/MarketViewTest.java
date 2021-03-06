package view;

import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameState;
import model.economy.Market;
import model.economy.Ware;
import model.factory.MaterialFactory;
import model.factory.WareFactory;
import model.gameobject.Airship;
import model.navigation.Harbor;

import common.Constants;

import controller.WindowController;

public class MarketViewTest {

    public static void main(String[] args) {

        MaterialFactory fac = MaterialFactory.getInstance();
        List<Ware> wares = WareFactory.getInstance().getWares();
        Airship testship = new Airship();
        try{
            testship.getStock().addTradeableWare(
                new Ware(
                    wares.get(0).getName(),
                    wares.get(0).getValue(),
                    wares.get(0).getWeight(),
                    wares.get(0).getPrice()), 20);
            testship.getStock().addTradeableWare(
                    new Ware(
                            wares.get(1).getName(),
                            wares.get(1).getValue(),
                            wares.get(1).getWeight(),
                            wares.get(1).getPrice()), 10);
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        Market market = new Market();
        Airship airship = new Airship();
        GameState.getInstance().setAirship(airship);
        GameState.getInstance().setCurrentHarbor(new Harbor(market, new Point(100,100), true));
        
        
        JFrame frame = new JFrame("Market view");
        JPanel pane = new MarketPanel();
        frame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        frame.setResizable(false);

        frame.setContentPane(pane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        WindowController.setFrame(frame);

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
    
    }
    
}
