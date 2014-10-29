package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Airship;

import common.Constants;
import controller.ButtonController;
import factory.MaterialFactory;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Material;

public class ConstructionView extends JPanel {

    private static final long serialVersionUID = 1L;
    private Airship airship;
    private MaterialFactory materialFactory;
    private  final ArrayList<Material> materials;

    public ConstructionView(Airship airship) {
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.airship = airship;

        this.materialFactory = MaterialFactory.getInstance();

        GridLayout mainLayout = new GridLayout(1, 2);
        this.setLayout(mainLayout);

        JPanel shipView = new JPanel();

        this.add(shipView);

        GridLayout tilesPicker = new GridLayout(5, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPicker);
        add(tilesPickerPanel);

        materials = this.materialFactory.getMaterials();

        for (int i = 0; i < materials.size(); i++) {
            
            JButton tileButton = new JButton(materials.get(i).getName());
            tileButton.setIcon(new ImageIcon(materials.get(i).getImage()));
            tileButton.addMouseListener(new ButtonController(materials.get(i)));
            add(tileButton);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
}
