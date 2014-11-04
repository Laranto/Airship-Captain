package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Airship;
import common.Constants;
import controller.ButtonController;
import controller.BrushController;

import java.awt.Button;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.Material;
import model.factory.MaterialFactory;

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
        
        JPanel selectionSide = new JPanel();
        this.add(selectionSide);
        
        JPanel toolsGridPanel = new JPanel(new FlowLayout());
        toolsGridPanel.setSize(Constants.WINDOW_WIDTH/2,Constants.WINDOW_HEIGHT/10);
        selectionSide.add(toolsGridPanel);
        
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty("id", "removeMaterial");
        removeTilesButton.addActionListener(new ButtonController());
        removeTilesButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        toolsGridPanel.add(removeTilesButton);
        

        GridLayout tilesPickerGrid = new GridLayout(5, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        selectionSide.add(tilesPickerPanel);

        materials = this.materialFactory.getMaterials();

        for (int i = 0; i < materials.size(); i++) {

            JButton tileButton = new JButton(materials.get(i).getName());
            tileButton.setIcon(new ImageIcon(materials.get(i).getImage()));
            tileButton.putClientProperty("id", "placeMaterial");
            tileButton.addMouseListener(new ButtonController(materials.get(i)));
            tileButton.addActionListener(new ButtonController());
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tilesPickerPanel.add(tileButton);
        }
        

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
}
