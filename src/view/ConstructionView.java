package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Airship;

import common.Constants;
import common.ConstructionBrush;
import controller.ButtonController;
import factory.MaterialFactory;
import java.awt.Button;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.Material;

public class ConstructionView extends JPanel {

    private static final long serialVersionUID = 1L;
    private Airship airship;
    private MaterialFactory materialFactory;
    private final ArrayList<Material> materials;

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
        
        GridLayout toolsGrid = new GridLayout(2, 1);
        JPanel toolsGridPanel = new JPanel(toolsGrid);
        add(toolsGridPanel);
        
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty("id", "removeMaterial");
        removeTilesButton.addActionListener(new ButtonController());
        toolsGridPanel.add(removeTilesButton);
        

        GridLayout tilesPickerGrid = new GridLayout(5, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        toolsGridPanel.add(tilesPickerPanel);

        materials = this.materialFactory.getMaterials();

        for (int i = 0; i < materials.size(); i++) {

            JButton tileButton = new JButton(materials.get(i).getName());
            tileButton.setIcon(new ImageIcon(materials.get(i).getImage()));
            tileButton.putClientProperty("id", "placeMaterial");
            tileButton.addMouseListener(new ButtonController(materials.get(i)));
            tileButton.addActionListener(new ButtonController());
            toolsGridPanel.add(tileButton);
        }
        
        

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
}
