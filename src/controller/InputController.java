package controller;

import common.Constants;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Airship;
import model.Material;

public class InputController implements MouseListener, KeyListener{

    private Airship airship;

    public InputController(Airship airship) {
        this.airship = airship;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Material m = ConstructionBrush.getMaterial();
        if(m == null)
        {
            return;
        }
        
        int tileX = e.getX()/Constants.TILE_SIZE;
        int tileY = e.getY()/Constants.TILE_SIZE;
        
        System.out.println(e.getX()+" "+e.getY()+" "+m+" "+tileX+" "+tileY);
        
        if(tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES)
        {
            this.airship.placeMaterial(m, tileX, tileY);
        }
        
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
