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

import model.GameState;
import model.factory.EntityFactory;
import model.factory.MaterialFactory;
import model.gameobject.Airship;
import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.ShipPart;
import common.Constants;
import common.enums.PropertyEnum;
import controller.ButtonController;
import controller.InputController;
import controller.ShipExportController;

public class ConstructionPanel extends GameDefaultPanel {

    private static final long serialVersionUID = 1L;
    private ConstructionStrategy strategy;
    
    public ConstructionPanel() {
        strategy = new ConstructionStrategy();
        InputController inputController = new InputController(strategy);
        ButtonController buttonController = new ButtonController(strategy);
        addMouseListener(inputController);
        addMouseMotionListener(inputController);
        addKeyListener(inputController);
        this.setFocusable(true);
        
        

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
        JPanel southPanel = createSouthPanel(buttonController);
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
        JPanel southPanel = createSouthPanel(buttonController);
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
    
    private void addInExportButton(JPanel panel) {
        ShipExportController controller = new ShipExportController();
        JButton exportButton = new JButton("Exportieren");
        exportButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.SAVE);
        exportButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        exportButton.addActionListener(controller);
        exportButton.setFocusable(false);
        panel.add(exportButton);
        JButton importButton = new JButton("Importieren");
        importButton.putClientProperty(Constants.BUTTON_PROPERTY_ID, PropertyEnum.SAVE);
        importButton.setBackground(Constants.BUTTON_BACKGROUND_INACTIVE);
        importButton.addActionListener(controller);
        importButton.setFocusable(false);
        panel.add(importButton);
        
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
	
	private JPanel createSouthPanel(ButtonController controller){
	    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    southPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        addSaveButton(controller,southPanel);
        addInExportButton(southPanel);
	    return southPanel;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        GameState.getInstance().getAirship().render(g2);
    }
    
    private class PreviewPanel extends JPanel{
        private static final long serialVersionUID = 1L;

        @Override
        public void paint(Graphics g) {
            if(strategy.getActivePlacement()!=null){
            super.paint(g);
            g.translate(getSize().width/2, getSize().height/2);
            if(strategy.getActivePlacement() instanceof ShipPart){
                ((ShipPart) strategy.getActivePlacement()).render((Graphics2D) g);
            }
            }
        }
    }
}
