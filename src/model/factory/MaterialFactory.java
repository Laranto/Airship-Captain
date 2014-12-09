package model.factory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import model.gameobject.Material;
import model.gameobject.material.Floor;
import model.gameobject.material.Wall;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import common.Constants;

public class MaterialFactory extends ShippartFactory<Material> {
    private static final String TYPE_FLOOR = "floor";
    private static final String TYPE_WALL = "wall";
    private static final String DOM_NODE_WEIGHT = "weight";
    private static final String DOM_NODE_DURABILITY = "durability";
    private static final String DOM_NODE_VALUE = "value";
    private static final String DOM_NODE_IMAGE = "image";
    private static final String DOM_NODE_MATERIAL = "material";
    private static final String DOM_NODE_NAME = "name";
    private static final String DOM_ATTR_TYPE = "type";
    private static MaterialFactory instance;
    private ArrayList<Material> materials;
    private static SAXBuilder builder = new SAXBuilder();
    
    private MaterialFactory() {
        materials = new ArrayList<Material>();
    }


    @Override
    public void parse() {
        File[] materialFolders = new File(Constants.FOLDER_MATERIAL).listFiles();
        for (int i = 0;i<materialFolders.length;i++) {
            if(materialFolders[i].isDirectory()){
                File[] xmlDefinitions = materialFolders[i].listFiles(new FilenameFilter() {
                    
                    @Override
                    public boolean accept(File dir , String name) {
                        return name.toLowerCase().endsWith(Constants.XML_FILE_ENDING);
                    }
                });
                if(xmlDefinitions.length>0)
                    try {
                        loadMaterial(xmlDefinitions[0]);
                    } catch (IOException | JDOMException e) {
                        // TODO Catch the error properly. Write an error log or something like that.
                        e.printStackTrace();
                    }
            }
        }
        
        

    }

    /**
     * Loads one XML with material definitions
     * @param filePath path of the xml file to be parsed and loaded.
     * @throws IOException 
     * @throws JDOMException 
     */
    private void loadMaterial(File xmlFile) throws IOException, JDOMException {
        
        Document doc = (Document) builder.build(xmlFile);
        List<Element> materials = doc.getRootElement().getChildren(DOM_NODE_MATERIAL);
        for (Element element : materials) {
            
            String   type=element.getAttribute(DOM_ATTR_TYPE).getValue()
                    ,name=element.getChildText(DOM_NODE_NAME)
                    ,imagePath=xmlFile.getParent()+File.separator+element.getChildText(DOM_NODE_IMAGE);
            int      value=Integer.parseInt(element.getChildText(DOM_NODE_VALUE))
                    ,durability=Integer.parseInt(element.getChildText(DOM_NODE_DURABILITY))
                    ,weight=Integer.parseInt(element.getChildText(DOM_NODE_WEIGHT));
            
            this.materials.add(createInstance(type, imagePath, name, durability, value, weight));
        }
    }


    /**
     * Creating an instance of a material.
     * @throws IOException when the image can not be loaded
     */
    private Material createInstance(String type, String imagePath, String name, int durability, int value, int weight) {

        Material m = createInstance(type,name,durability,value,weight,imagePath);
        return m;
    }

    /**
     * Utility for createInstace Methods.
     */
    private Material createInstance(String type , String name , int durability , int value , int weight, String imagePath) {
        Material m = null;
        switch (type) {
            default:
            case TYPE_WALL:
                m = new Wall(name,value,weight,durability,imagePath);
                break;
            case TYPE_FLOOR:
                m = new Floor(name,value,weight,durability,imagePath);
                break;
        }
        return m;
    }


    /**
     * Return all the materials, parses if the materials have not been parsed before.
     * @return 
     */
    public ArrayList<Material> getMaterials() {
        if(materials.isEmpty()){
            parse();
        }
        return materials;
    }

    public static MaterialFactory getInstance() {
        if(instance == null){
            instance = new MaterialFactory();
        }
        return instance;
    }


    @Override
    public Material instanzise(Material prototype) {
        if(!(prototype instanceof Material)){
            throw new RuntimeErrorException(new Error("Added prototype was not a Material." + prototype.toString()));
        }
        Material materialPrototype = (Material) prototype;
        String type = TYPE_FLOOR; 
        if(prototype instanceof Wall){
            type = TYPE_WALL;
        }
        
        return createInstance(type
                , materialPrototype.getImagePath()
                , materialPrototype.getName()
                , materialPrototype.getDurability()
                , materialPrototype.getValue()
                , materialPrototype.getWeight());
    }

}
