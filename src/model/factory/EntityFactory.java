package model.factory;

import java.awt.Dimension;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.management.RuntimeErrorException;

import model.gameobject.Entity;
import model.gameobject.entity.Weapon;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import common.Constants;

public class EntityFactory extends ShippartFactory<Entity> {
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
    private static final String DOM_NODE_DAMAGE = "damage";
    private static SAXBuilder builder = new SAXBuilder();
    private ArrayList<Entity> entities;
    private static EntityFactory instance;
    private static final FilenameFilter XML_FILES_AND_FOLDERS_FILTER =  new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(
                    Constants.XML_FILE_ENDING);
        }
    };

	private EntityFactory() {
		entities = new ArrayList<Entity>();
	};

	@Override
    public void parse() {
        File[] files = new File(Constants.FOLDER_OBJECTS).listFiles();
        parseFiles(files);

    }

    private void parseFiles(File[] files) {
        for (File file:files) {
            if (file.isDirectory()) {
                parseFiles(file.listFiles(XML_FILES_AND_FOLDERS_FILTER));
            }else{
                try {
                    entities.addAll(load(file));
                } catch (IOException | JDOMException e) {
                    // TODO Catch the error properly. Write an error log or
                    // something like that.
                    System.out.println(file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Entity> load(File xmlFile) throws IOException, JDOMException {
        Document doc = (Document) builder.build(xmlFile);
        List<Entity> entityList = new ArrayList<Entity>();
        List<Element> elements = doc.getRootElement().getChildren(DOM_NODE_OBJECT);
        String imagePath = xmlFile.getParent()+ File.separator;
        for (Element element : elements) {
            entityList.add(loadElement(element, imagePath));
        }
        return entityList;
    }

    private Entity loadElement(Element element, String imagePath) {
        String type = element.getAttributeValue(DOM_ATTR_TYPE);
        switch (type) {
            case TYPE_WEAPON:
                return createWeapon(element, imagePath, Constants.ENTITY_ORIENTATION_RIGHT);
        }
        return null;
    }
	
    /**
     * Creating an instance of a material. Keeps rotation.
     */
    private Entity createWeapon(Element element, String imagePath ,Vector<Integer> orientation){
        String  name = element.getChildText(DOM_NODE_NAME);
                imagePath += element.getChildText(DOM_NODE_IMAGE);
                
        int     width = Integer.parseInt(element.getAttribute(DOM_ATTR_WIDTH).getValue()), 
                height = Integer.parseInt(element.getAttribute(DOM_ATTR_HEIGHT).getValue()), 
                value = Integer.parseInt(element.getChildText(DOM_NODE_VALUE)), 
                durability = Integer.parseInt(element.getChildText(DOM_NODE_DURABILITY)), 
                weight = Integer.parseInt(element.getChildText(DOM_NODE_WEIGHT)),
                damage = Integer.parseInt(element.getChildText(DOM_NODE_DAMAGE));
        
        return createWeapon(imagePath, name, value, durability, weight, width,
                height, orientation, damage);
    }

    private Weapon createWeapon(String imagePath, String name, int value, int durability, int weight, int width, int height, Vector<Integer> orientation, int damage) {
        return new Weapon(name, value, weight, durability, imagePath, orientation, new Dimension(width, height), damage);
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
    public Entity instanzise(Entity prototype) {
        if (!(prototype instanceof Entity)) {
            throw new RuntimeErrorException(new Error("Added prototype was not an Entity." + prototype.toString()));
        }
        Entity entityPrototype = (Entity) prototype;
        if (prototype instanceof Weapon) {
            return createWeapon(
                entityPrototype.getImagePath()
                ,entityPrototype.getName()
                ,entityPrototype.getValue()
                ,entityPrototype.getDurability()
                ,entityPrototype.getWeight()
                ,(int) entityPrototype.getSize().getWidth()
                ,(int) entityPrototype.getSize().getHeight()
                , entityPrototype.getOrientation()
                , ((Weapon)entityPrototype).getDamage());
        }
        return null;
    }



}
