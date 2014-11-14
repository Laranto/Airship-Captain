package view;

import handler.ConstructionStrategy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.Material;
import common.Constants;
import common.enums.PropertyEnum;
import controller.ButtonController;
import controller.InputController;

public class ConstructionPanel extends GameDefaultPanel {

    private static final long serialVersionUID = 1L;
    private Airship airship;

    public ConstructionPanel(Airship airship) {
        ConstructionStrategy strategy = new ConstructionStrategy(airship);
        addMouseListener(new InputController(strategy));
        addMouseMotionListener(new InputController(strategy));
        ButtonController buttonController = new ButtonController(strategy);
        
        this.airship = airship;
        

        GridLayout mainLayout = new GridLayout(1, 2);
        this.setLayout(mainLayout);

        JPanel shipView = new JPanel();
        this.add(shipView);
        
        JTabbedPane selectionSide = new JTabbedPane();
        this.add(selectionSide);
        
        JPanel materialPanel = createMaterialPanel(buttonController);
        selectionSide.addTab("Material", materialPanel);
        
        JPanel entityPanel = createEntityPanel(buttonController);
        selectionSide.addTab("Gegenstände",entityPanel);
    }

	private JPanel createEntityPanel(ButtonController buttonController) {
		JPanel materialPanel = new JPanel();
        JPanel toolsGridPanel = createToolGridPanel();
        materialPanel.add(toolsGridPanel);
        
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.DELETE_MATERIAL);
        removeTilesButton.addActionListener(buttonController);
        removeTilesButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        toolsGridPanel.add(removeTilesButton);
        

        GridLayout tilesPickerGrid = new GridLayout(5, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        materialPanel.add(tilesPickerPanel);

        ArrayList<Entity> entities = EntityFactory.getInstance().getEntities();
        for (int i = 0; i < entities.size(); i++) {
            JButton tileButton = new JButton(entities.get(i).getName());
            tileButton.setIcon(new ImageIcon(entities.get(i).getImage()));
            tileButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, entities.get(i));
            tileButton.addActionListener(buttonController);
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tilesPickerPanel.add(tileButton);
        }
        
		return materialPanel;
	}

	private JPanel createMaterialPanel(ButtonController buttonController) {
		
		JPanel materialPanel = new JPanel();
        JPanel toolsGridPanel = createToolGridPanel();
        materialPanel.add(toolsGridPanel);
        
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.DELETE_MATERIAL);
        removeTilesButton.addActionListener(buttonController);
        removeTilesButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        toolsGridPanel.add(removeTilesButton);
        

        GridLayout tilesPickerGrid = new GridLayout(5, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        materialPanel.add(tilesPickerPanel);

        ArrayList<Material> materials = MaterialFactory.getInstance().getMaterials();
        for (int i = 0; i < materials.size(); i++) {
            JButton tileButton = new JButton(materials.get(i).getName());
            tileButton.setIcon(new ImageIcon(materials.get(i).getImage()));
            tileButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, materials.get(i));
            tileButton.addActionListener(buttonController);
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tilesPickerPanel.add(tileButton);
        }
		return materialPanel;
	}

	private JPanel createToolGridPanel() {
		JPanel toolsGridPanel = new JPanel(new FlowLayout());
        toolsGridPanel.setSize(Constants.WINDOW_WIDTH/2,Constants.WINDOW_HEIGHT/10);
		return toolsGridPanel;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
}
