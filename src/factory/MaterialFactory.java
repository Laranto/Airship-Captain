package factory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import model.Material;
import model.ShipPart;
import model.material.Floor;
import model.material.Wall;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import common.Constants;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 * @author Laranto
 */
public class MaterialFactory extends ShippartFactory {
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
        List<Element> materials = doc.getRootElement().getChildren("material");
        for (Element element : materials) {
            
            String   type=element.getAttribute("type").getValue()
                    ,name=element.getChildText("name")
                    ,imagePath=xmlFile.getParent()+File.separator+element.getChildText("image");
            int      value=Integer.parseInt(element.getChildText("value"))
                    ,durability=Integer.parseInt(element.getChildText("durability"))
                    ,weight=Integer.parseInt(element.getChildText("weight"));
            
            createInstance(type, imagePath, name, durability, value, weight);
        }
    }


    /**
     * Creating an instance of a material and adds it to the material list
     * @throws IOException 
     */
    private void createInstance(String type, String imagePath, String name, int durability, int value, int weight) throws IOException {

        Material m = null;
        switch (type) {
            default:
            case "wall":
                m = new Wall();
                break;
            case "floor":
                m = new Floor();
                break;
        }

        m.setDurability(durability);
        m.setImage(imagePath);
        m.setName(name);
        m.setValue(value);
        m.setWeight(weight);

        materials.add(m);
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
    public ShipPart instanzise(ShipPart prototype) {
        if(!(prototype instanceof Material)){
            throw new RuntimeErrorException(new Error("Added prototype was not a Material." + prototype.toString()));
        }
        Material materialPrototype = (Material) prototype;
        Material duplicate;
        if(prototype instanceof Wall){
            duplicate = new Wall();
        }else{
            duplicate = new Floor();
        }
        duplicate.setDurability(materialPrototype.getDurability());
        duplicate.setImage(materialPrototype.getImage());
        duplicate.setName(materialPrototype.getName());
        duplicate.setValue(materialPrototype.getValue());
        duplicate.setWeight(materialPrototype.getWeight());
        return duplicate;
    }

}
