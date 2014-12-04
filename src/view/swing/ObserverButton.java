package view.swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

public class ObserverButton extends JButton implements Observer {

    public ObserverButton(String text) {
        super(text);
    }

    @Override
    public void update(Observable o, Object arg) {
        setEnabled(!isEnabled());
    }

}
