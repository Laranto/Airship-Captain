package controller;

import handler.HandlerStrategy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listening to keyboard or mouse inputs of the user and 
 * sends them to the given strategy to handle the event.
 * 
 */
public class InputController extends MouseAdapter implements KeyListener {

    private HandlerStrategy strategy;
    
    public InputController(HandlerStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        strategy.mouseEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        strategy.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        strategy.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        strategy.keyTyped(e);
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
        strategy.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        strategy.mouseMoved(e);
    }
}
