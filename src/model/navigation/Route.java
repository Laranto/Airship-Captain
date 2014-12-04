package model.navigation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Vector;

import model.GameState;
import model.factory.ScenarioFactory;
import model.gameobject.Renderable;

import common.Constants;
import common.Utils;


public class Route implements Renderable{

    private Harbor to;
    private Shape line;
    private int remainingDuration;
    private Vector<Integer> directedDistance;
    private Vector<Integer> directedStep;
    private double battleChance;
    private Scenario scenario;
    
    public Route(){
        this(null);
    }
    
    @SuppressWarnings("serial")
    public Route(Harbor to){
        this.to = to;
        directedDistance = new Vector<Integer>(2){{
        	add(0, 0);
        	add(1, 0);
        }};
        directedStep = new Vector<Integer>(2){{
        	add(0, 0);
        	add(1, 0);
        }};
        
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
    
    public Double calculateDistance(){
        if(!isCorrectRoute()){
            return 0.0;
        }
        int x1 = (int) getFrom().getPosition().getX(),
            x2 = (int) to.getPosition().getX(),
            y1 = (int) getFrom().getPosition().getY(),
            y2 = (int) to.getPosition().getY();
        directedDistance.set(0, x2-x1);
        directedDistance.set(1, y2-y1);
        return Math.sqrt(Math.pow(directedDistance.get(0),2) + Math.pow(directedDistance.get(1), 2));
    }

    public int calculateDuration(){
        return (int)(calculateDistance()/GameState.getInstance().getAirship().getSpeed());
    }

    private boolean isCorrectRoute() {
        return getFrom() != null && to != null;
    }

    @Override
    public void render(Graphics2D g) {
        if(isCorrectRoute()){
            int radius = (int)Constants.HARBOR_CIRCLE_DIAMETER/2;
            Point2D fromPosition = new Point((int)getFrom().getPosition().getX()+radius, (int)getFrom().getPosition().getY()+radius);
            Point2D toPosition = new Point((int)to.getPosition().getX()+radius, (int)to.getPosition().getY()+radius); 
            if(isTravelling()){
            	drawTravelLine(g, fromPosition, toPosition);
            }else{
                drawLines(g, fromPosition, toPosition);
            }
            getFrom().render(g);
            getTo().render(g);
        }
    }

    private void drawTravelLine(Graphics2D g, Point2D fromPosition, Point2D toPosition) {
        Point2D currentPosition = new Point((int)toPosition.getX()-directedStep.get(0)*remainingDuration, 
        									(int)toPosition.getY()-directedStep.get(1)*remainingDuration);
        Shape progressLine = new Line2D.Double(fromPosition, currentPosition);
        Color tmpCol = g.getColor();
        Stroke tmpStroke = g.getStroke();
        g.setStroke(Constants.STROKE_DASHED);
        g.setColor(Color.red);
        g.draw(progressLine);
        g.setStroke(tmpStroke);
        g.setColor(tmpCol);
    }

    private void drawLines(Graphics2D g, Point2D fromPosition, Point2D toPosition) {
        line = new Line2D.Double(fromPosition, toPosition);
        Stroke tmp = g.getStroke();
        g.setStroke(Constants.STROKE_SOLID);
        g.draw(line);
        g.setStroke(tmp);
    }

    public void startTravel() {
        setRemainingDuration(calculateDuration());
        directedStep.set(0, directedDistance.get(0)/getRemainingDuration());
        directedStep.set(1, directedDistance.get(1)/getRemainingDuration());
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

    public boolean travel() {
        travelStep();
        if(getRemainingDuration()==0){          
            endTravel();
            return false;
        }
        return true;
    }

    private void travelStep() {
        try {
        	System.out.println("travelling");
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
    	System.out.println("Ended Travelling!");
        GameState.getInstance().setCurrentHarbor(to);
    }

	public boolean isTravelling() {
	    return getRemainingDuration()!=0;
    }
}
