package factory;

import common.Constants;
import common.XMLParser;
import java.awt.List;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import model.Material;
import model.material.Floor;
import model.material.Wall;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hesyar Uzuner <info@hesyar.com>
 */
public class MaterialFactory extends ShippartFactory {

    private ArrayList<Material> materials;

    public MaterialFactory() {
        materials = new ArrayList<Material>();
    }

    @Override
    public void instanzise() {
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
                    } catch (IOException e) {
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
     */
    private void loadMaterial(File xmlFile) throws IOException {
        Document doc = XMLParser.parse(xmlFile);

        NodeList nodeList = doc.getElementsByTagName("material");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            //Getting the attributes
            String type = node.getAttributes().getNamedItem("type").getNodeValue();
            int width = Integer.parseInt(node.getAttributes().getNamedItem("width").getNodeValue());
            int height = Integer.parseInt(node.getAttributes().getNamedItem("height").getNodeValue());

            String name = node.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
            String imagePath = xmlFile.getParent() + File.separator + node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue();
            int value = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());
            int durability = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());
            int weight = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());

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

    /**
     * Example usage
     */
    public static void main(String[] args) {
        MaterialFactory mf = new MaterialFactory();
        ArrayList<Material> materials = mf.getMaterials();
        
        for(int i=0; i<materials.size();i++)
        {
            Material m = materials.get(i);
            
            
            
            System.out.println(m.getName()+"  "+m.getImage()+" " +m.getClass());
            
        }
        
    }

}
