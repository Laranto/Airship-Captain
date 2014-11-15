package view;

import handler.ConstructionStrategy;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
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
    private ConstructionStrategy strategy;
    
    public ConstructionPanel(Airship airship) {
        strategy = new ConstructionStrategy(airship);
        InputController inputController = new InputController(strategy);
        ButtonController buttonController = new ButtonController(strategy);
        addMouseListener(inputController);
        addMouseMotionListener(inputController);
        addKeyListener(inputController);
        this.setFocusable(true);
        
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
		entityPanel.setLayout(new BorderLayout());
        JPanel toolsGridPanel = createToolGridPanel();
        entityPanel.add(toolsGridPanel);
        entityPanel.add(toolsGridPanel,BorderLayout.NORTH);
        
        addRemoveButton(buttonController, toolsGridPanel,PropertyEnum.DELETE_ENTITY);
        JPanel southPanel = createSouthPanel();
        addSaveButton(buttonController,southPanel);
        JPanel previewPanel = new PreviewPanel();
        previewPanel.setPreferredSize(new Dimension(Constants.TILE_SIZE*3, Constants.TILE_SIZE*3));
        //TODO Add Tooltip
        southPanel.add(previewPanel);
        
        entityPanel.add(southPanel,BorderLayout.SOUTH);
        
        JButton moveButton = new JButton("Gegenstand bewegen");
        moveButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.MOVE);
        moveButton.addActionListener(buttonController);
        moveButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        moveButton.setFocusable(false);
        toolsGridPanel.add(moveButton);
        
        ArrayList<Entity> entities = EntityFactory.getInstance().getEntities();
        GridLayout tilesPickerGrid = new GridLayout(entities.size()/2, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        for (int i = 0; i < entities.size(); i++) {
            JButton tileButton = new JButton(entities.get(i).getName());
            tileButton.setIcon(new ImageIcon(entities.get(i).getImage()));
            tileButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, entities.get(i));
            tileButton.addActionListener(buttonController);
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tileButton.setFocusable(false);
            tilesPickerPanel.add(tileButton);
        }
        JScrollPane scroll = new JScrollPane(tilesPickerPanel);
        entityPanel.add(scroll,BorderLayout.CENTER);
        
		return entityPanel;
	}

    private JPanel createMaterialPanel(ButtonController buttonController) {
		
		JPanel materialPanel = new JPanel();
		materialPanel.setLayout(new BorderLayout());
        JPanel toolsGridPanel = createToolGridPanel();
        materialPanel.add(toolsGridPanel,BorderLayout.NORTH);
        
        addRemoveButton(buttonController, toolsGridPanel,PropertyEnum.DELETE_MATERIAL);
        JPanel southPanel = createSouthPanel();
        addSaveButton(buttonController,southPanel);
        materialPanel.add(southPanel,BorderLayout.SOUTH);

        
        ArrayList<Material> materials = MaterialFactory.getInstance().getMaterials();

        GridLayout tilesPickerGrid = new GridLayout(materials.size()/2, 2);
        JPanel tilesPickerPanel = new JPanel(tilesPickerGrid);
        
        for (int i = 0; i < materials.size(); i++) {
            JButton tileButton = new JButton(materials.get(i).getName());
            tileButton.setIcon(new ImageIcon(materials.get(i).getImage()));
            tileButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, materials.get(i));
            tileButton.addActionListener(buttonController);
            tileButton.setHorizontalAlignment(SwingConstants.LEFT);
            tileButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
            tileButton.setFocusable(false);
            tilesPickerPanel.add(tileButton);
        }
        
        JScrollPane scroll = new JScrollPane(tilesPickerPanel);
        materialPanel.add(scroll,BorderLayout.CENTER);
        
		return materialPanel;
	}

    private void addSaveButton(ButtonController buttonController , JPanel toolsGridPanel) {
        JButton saveButton = new JButton("Speichern");
        saveButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.SAVE);
        saveButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        saveButton.addActionListener(buttonController);
        saveButton.setFocusable(false);
        toolsGridPanel.add(saveButton);
    }
    
    public void addRemoveButton(ButtonController buttonController , JPanel toolsGridPanel, PropertyEnum removeType) {
        JButton removeTilesButton = new JButton("Entfernen");
        removeTilesButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, removeType);
        removeTilesButton.addActionListener(buttonController);
        removeTilesButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        removeTilesButton.setFocusable(false);
        toolsGridPanel.add(removeTilesButton);
    }

	private JPanel createToolGridPanel() {
		JPanel toolsGridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolsGridPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		toolsGridPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/16));
		return toolsGridPanel;
	}
	
	private JPanel createSouthPanel(){
	    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    southPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
	    
	    return southPanel;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        airship.render(g2);
    }
    
    private class PreviewPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            if(strategy.getActivePlacement()!=null){
            super.paint(g);
            g.translate(getSize().width/2, getSize().height/2);
            strategy.getActivePlacement().render((Graphics2D) g);
            }
        }
    }
}
