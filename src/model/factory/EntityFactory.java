package model.factory;

import common.Constants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Entity;
import model.Material;
import model.ShipPart;
import model.entity.Weapon;
import model.material.Floor;
import model.material.Wall;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import sun.applet.Main;

/**
 *
 */
public class EntityFactory extends ShippartFactory {

    private static final String DOM_NODE_OBJECT = "object";
    private static final String DOM_NODE_NAME = "name";
    private static final String DOM_ATTR_TYPE = "type";
    private static final String DOM_NODE_IMAGE = "image";
    private static final String DOM_NODE_WEIGHT = "weight";
    private static final String DOM_NODE_VALUE = "value";

    private static EntityFactory instance;

    private static SAXBuilder builder = new SAXBuilder();

    private ArrayList<Entity> entities;

    private EntityFactory() {
        entities = new ArrayList<Entity>();
    }

    ;
    

    @Override
    public void parse() {
        File[] materialFolders = new File(Constants.FOLDER_OBJECTS).listFiles();
        for (int i = 0; i < materialFolders.length; i++) {
            if (materialFolders[i].isDirectory()) {
                File[] xmlDefinitions = materialFolders[i].listFiles(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(Constants.XML_FILE_ENDING);
                    }
                });
                if (xmlDefinitions.length > 0) {
                    try {
                        loadObject(xmlDefinitions[0]);
                    } catch (IOException | JDOMException e) {
                        // TODO Catch the error properly. Write an error log or something like that.
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private void loadObject(File xmlFile) throws IOException, JDOMException {

        Document doc = (Document) builder.build(xmlFile);
        List<Element> materials = doc.getRootElement().getChildren(DOM_NODE_OBJECT);
        for (Element element : materials) {

            String type = element.getAttribute(DOM_ATTR_TYPE).getValue(), name = element.getChildText(DOM_NODE_NAME), imagePath = xmlFile.getParent() + File.separator + element.getChildText(DOM_NODE_IMAGE);
            int value = Integer.parseInt(element.getChildText(DOM_NODE_VALUE)), weight = Integer.parseInt(element.getChildText(DOM_NODE_WEIGHT));

            createInstance(type, imagePath, name, value, weight);
        }
    }

    private void createInstance(String type, String imagePath, String name, int value, int weight) throws IOException {

        Entity o = null;
        switch (type) {
            default:
            case "weapon":
                o = new Weapon();
                break;
        }

        o.setName(name);
        o.setValue(value);
        o.setWeight(weight);

        entities.add(o);
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
        // TODO Auto-generated method stub
        return null;
    }

 
}
