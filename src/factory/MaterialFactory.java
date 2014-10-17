package factory;

import common.XMLParser;
import java.awt.List;
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
        Document doc = XMLParser.parse("settings/materials.xml");

        NodeList nodeList = doc.getElementsByTagName("material");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            //Getting the attributes
            String type = node.getAttributes().getNamedItem("type").getNodeValue();
            int width = Integer.parseInt(node.getAttributes().getNamedItem("width").getNodeValue());
            int height = Integer.parseInt(node.getAttributes().getNamedItem("height").getNodeValue());

            String name = node.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
            String imagePath = node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue();
            int value = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());
            int durability = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());
            int weight = Integer.parseInt(node.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getNodeValue());

            createInstance(type, imagePath, name, durability, value, weight);
        }

    }
    
    
    /**
     * Creating an instance of a material and adds it to the material list
     */
    private void createInstance(String type, String imagePath, String name, int durability, int value, int weight) {

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
     * Return all the materials 
     * @return 
     */
    public ArrayList<Material> getMaterials() {
        parse();
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
