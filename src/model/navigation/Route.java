package model.navigation;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import model.GameState;
import model.factory.ScenarioFactory;
import model.gameobject.Renderable;

import common.Constants;
import common.Utils;


public class Route implements Renderable{

    private Harbor to;
    private Shape line;
    private int remainingDuration;
    private double battleChance;
    private Scenario scenario;
    
    public Route(){
        this(null);
    }
    
    public Route(Harbor to){
        this.to = to;
    }
    
    public Harbor getFrom(){
        return GameState.getInstance().getCurrentHarbor();
    }
    
    public Harbor getTo(){
        return to;
    }
    
    public void setTo(Harbor harbor){
        to = harbor;
    }
    
    public Double getDistance(){
        if(!isCorrectRoute()){
            return 0.0;
        }
        int x1 = (int) getFrom().getPosition().getX(),
            x2 = (int) to.getPosition().getX(),
            y1 = (int) getFrom().getPosition().getY(),
            y2 = (int) to.getPosition().getY();
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    public int getDuration(){
        return (int)(getDistance()/GameState.getInstance().getAirship().getSpeed());
    }

    private boolean isCorrectRoute() {
        return getFrom() != null && to != null;
    }

    @Override
    public void render(Graphics2D g) {
        if(isCorrectRoute()){
            int radius = (int)Constants.HARBOR_CIRCLE_DIAMETER/2;
            line = new Line2D.Double((int)getFrom().getPosition().getX()+radius,
                    (int)getFrom().getPosition().getY()+radius,
                    (int)to.getPosition().getX()+radius,
                    (int)to.getPosition().getY()+radius);
            Stroke tmp = g.getStroke();
//            g.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            g.setStroke(new BasicStroke(4));
            g.draw(line);
            g.setStroke(tmp);
            getFrom().render(g);
            to.render(g);
        }
    }

    public void removeLines(Graphics2D g) {
        if(!isCorrectRoute() && line != null){
            g.clearRect(
                    (int)line.getBounds().getX(), 
                    (int)line.getBounds().getY(), 
                    (int)line.getBounds().getWidth(),
                    (int)line.getBounds().getHeight());
            line = null;
        }
    }

    public void start() {
        setRemainingDuration(getDuration());
        battleChance = 0.1;
}
    
    public int getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public boolean hasActiveScenario() {
        return scenario == null?false:scenario.isActive();
    }

    public void travel() {
        if(getRemainingDuration() > 0){          
            travelStep();
        }else{
            endTravel();
        }
    }

    private void travelStep() {
        try {
            int fightProbabilty = Utils.getRandomIntBetween(0, 100);
            if (battleChance * 100 > fightProbabilty) {
                scenario = ScenarioFactory.build();
                scenario.show();
                battleChance = 0.1;
            }
            Thread.sleep(1000);
            
            // TODO replace these numbers with an constant
            battleChance *= 1.2;
            setRemainingDuration(getRemainingDuration() - 1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void endTravel() {
        GameState.getInstance().getCurrentHarbor().setActive(false);
        to.setActive(true);
        to.setNextDestination(false);
        GameState.getInstance().setCurrentHarbor(to);
    }
}
