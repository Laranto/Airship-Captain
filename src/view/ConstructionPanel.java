package view;

import handler.ConstructionStrategy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
		JPanel entityPanel = new JPanel();
        JPanel toolsGridPanel = createToolGridPanel();
        entityPanel.add(toolsGridPanel);
        
        addRemoveButton(buttonController, toolsGridPanel,PropertyEnum.DELETE_ENTITY);
        addSaveButton(buttonController,toolsGridPanel);
        
        JButton moveButton = new JButton("Gegenstand bewegen");
        moveButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.MOVE);
        moveButton.addActionListener(buttonController);
        moveButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        toolsGridPanel.add(moveButton);
        
        ArrayList<Entity> entities = EntityFactory.getInstance().getEntities();
        GridLayout tilesPickerGrid = new GridLayout(entities.size()/2, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        entityPanel.add(tilesPickerPanel);
        for (int i = 0; i < entities.size(); i++) {
            JButton tileButton = new JButton(entities.get(i).getName());
            tileButton.setIcon(new ImageIcon(entities.get(i).getImage()));
            tileButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, entities.get(i));
            tileButton.addActionListener(buttonController);
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tilesPickerPanel.add(tileButton);
        }
        
		return entityPanel;
	}

    private JPanel createMaterialPanel(ButtonController buttonController) {
		
		JPanel materialPanel = new JPanel();
        JPanel toolsGridPanel = createToolGridPanel();
        materialPanel.add(toolsGridPanel);
        
        addRemoveButton(buttonController, toolsGridPanel,PropertyEnum.DELETE_MATERIAL);
        addSaveButton(buttonController,toolsGridPanel);

        
        ArrayList<Material> materials = MaterialFactory.getInstance().getMaterials();

        GridLayout tilesPickerGrid = new GridLayout(materials.size()/2, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        materialPanel.add(tilesPickerPanel);
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

    private void addSaveButton(ButtonController buttonController , JPanel toolsGridPanel) {
        JButton saveButton = new JButton("Speichern");
        saveButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.SAVE);
        saveButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        saveButton.addActionListener(buttonController);
        toolsGridPanel.add(saveButton);
    }
    
    public void addRemoveButton(ButtonController buttonController , JPanel toolsGridPanel, PropertyEnum removeType) {
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, removeType);
        removeTilesButton.addActionListener(buttonController);
        removeTilesButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        toolsGridPanel.add(removeTilesButton);
    }

	private JPanel createToolGridPanel() {
		JPanel toolsGridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolsGridPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 1, 3, Color.BLACK));
		toolsGridPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/16));
		return toolsGridPanel;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
}
