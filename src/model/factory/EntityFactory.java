package model.factory;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.management.RuntimeErrorException;

import model.gameobject.Entity;
import model.gameobject.Material;
import model.gameobject.ShipPart;
import model.gameobject.entity.Weapon;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import common.Constants;

/**
 *
 */
public class EntityFactory extends ShippartFactory {
	private static final String TYPE_WEAPON = "weapon";
    private static final String DOM_NODE_OBJECT = "object";
	private static final String DOM_NODE_NAME = "name";
	private static final String DOM_ATTR_TYPE = "type";
	private static final String DOM_NODE_IMAGE = "image";
	private static final String DOM_NODE_WEIGHT = "weight";
	private static final String DOM_NODE_VALUE = "value";
	private static final String DOM_NODE_DURABILITY = "durability";
    private static final String DOM_ATTR_WIDTH = "width";
    private static final String DOM_ATTR_HEIGHT = "height";

    /**
     * Default Orientation pointing right
     */
    @SuppressWarnings("all") //Hush, everything is oke
    private static final Vector<Integer> DEFAULT_ORIENTATION=new Vector<>(new ArrayList(){{add(1);add(0);}}
    );
    
    
	private static EntityFactory instance;

	private static SAXBuilder builder = new SAXBuilder();

	private ArrayList<Entity> entities;

	private EntityFactory() {
		entities = new ArrayList<Entity>();
	};

	@Override
	public void parse() {
		File[] materialFolders = new File(Constants.FOLDER_OBJECTS).listFiles();
		for (int i = 0; i < materialFolders.length; i++) {
			if (materialFolders[i].isDirectory()) {
				File[] xmlDefinitions = materialFolders[i]
						.listFiles(new FilenameFilter() {

							@Override
							public boolean accept(File dir, String name) {
								return name.toLowerCase().endsWith(
										Constants.XML_FILE_ENDING);
							}
						});
				if (xmlDefinitions.length > 0) {
					try {
						loadObject(xmlDefinitions[0]);
					} catch (IOException | JDOMException e) {
						// TODO Catch the error properly. Write an error log or
						// something like that.
						e.printStackTrace();
					}
				}
			}
		}

	}

	private void loadObject(File xmlFile) throws IOException, JDOMException {

		Document doc = (Document) builder.build(xmlFile);
		List<Element> materials = doc.getRootElement().getChildren(
				DOM_NODE_OBJECT);
		for (Element element : materials) {

			String type = element.getAttribute(DOM_ATTR_TYPE).getValue(), 
			        name = element.getChildText(DOM_NODE_NAME), 
					imagePath = xmlFile.getParent()+ File.separator+ element.getChildText(DOM_NODE_IMAGE);
			int 	width        = Integer.parseInt(element.getAttribute(DOM_ATTR_WIDTH).getValue()),
			        height       = Integer.parseInt(element.getAttribute(DOM_ATTR_HEIGHT).getValue()),
			        value        = Integer.parseInt(element.getChildText(DOM_NODE_VALUE)), 
					durability   = Integer.parseInt(element.getChildText(DOM_NODE_DURABILITY)), 
					weight       = Integer.parseInt(element.getChildText(DOM_NODE_WEIGHT));
			

            this.entities.add(createInstance(type, imagePath, name, durability, value, weight,width,height));

		}
	}
	
    /**
     * Creating an instance of a material. Do not use this when you have the image already loaded.
     * @throws IOException when the image can not be loaded
     */
    private Entity createInstance(String type, String imagePath, String name, int durability, int value, int weight,int width,int height) throws IOException {

    	Entity m = createInstance(type,name,durability,value,weight,width,height,null);

        m.setImage(imagePath);
        return m;
    }
    /**
     * Creating an instance of a material. Does not parse the image again. If the image has been loaded this should be used.
     */
    private Entity createInstance(String type, BufferedImage image, String name, int durability, int value, int weight,int width,int height){
        
    	Entity m = createInstance(type,name,durability,value,weight,width,height,image);
        m.setImage(image);
        return m;
    }

	private Entity createInstance(String type, String name, int durability, int value, int weight,int width, int height,BufferedImage image) {

		Entity o = null;
		switch (type) {
		default:
		case "weapon":
			o = new Weapon(name,durability,value,weight,image, DEFAULT_ORIENTATION, new Dimension(width,height));
			break;
		}
		return o;
	}

	public ArrayList<Entity> getEntities() {
		if (entities.isEmpty()) {
			parse();
		}
		return entities;
	}

	public static EntityFactory getInstance() {
		if (instance == null) {
			instance = new EntityFactory();
		}
		return instance;
	}

	@Override
	public ShipPart instanzise(ShipPart prototype) {
		if (!(prototype instanceof Entity)) {
			throw new RuntimeErrorException(
					new Error("Added prototype was not an Entity."
							+ prototype.toString()));
		}
		Entity entityPrototype = (Entity) prototype;
		String type = "";
		if (prototype instanceof Weapon) {
			type = TYPE_WEAPON;
		}

		return createInstance(	type, 
								entityPrototype.getImage(),
								entityPrototype.getName(), 
								entityPrototype.getDurability(),
								entityPrototype.getValue(),
								entityPrototype.getWeight(),
								(int)entityPrototype.getSize().getWidth(),
								(int)entityPrototype.getSize().getHeight());
	}

}
