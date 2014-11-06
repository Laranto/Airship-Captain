package controller;

import common.Constants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Airship;
import model.Material;

public class InputController implements MouseListener, MouseMotionListener, KeyListener {

    private Airship airship;

    public InputController(Airship airship) {
        this.airship = airship;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.performMouseAction(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.performMouseAction(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void performMouseAction(MouseEvent e) {

        int tileX = e.getX() / Constants.TILE_SIZE;
        int tileY = e.getY() / Constants.TILE_SIZE;

        switch (BrushController.getConstructionState()) {
            case 0:
                Material m = BrushController.getMaterial();

                if (m == null) {
                    return;
                }

                if (tileX < Constants.AIRSHIP_WIDTH_TILES && tileY < Constants.AIRSHIP_HEIGHT_TILES) {
                    this.airship.placeMaterial(m, tileX, tileY);
                }
                break;
            case 1:
                this.airship.removeMaterial(tileX, tileY);
                break;
        }

    }

}
