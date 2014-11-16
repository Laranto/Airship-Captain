package model.factory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.economy.Ware;
import model.economy.ware.Food;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import common.Constants;

public class WareFactory {

    private static WareFactory instance;
    private ArrayList<Ware> wares;
    
    private static final String TYPE_DRINK = "drink";
    
    private static final String DOM_NODE_WARE = "ware";
    private static final String DOM_NODE_WEIGHT = "weight";
    private static final String DOM_NODE_VALUE = "value";
    private static final String DOM_NODE_NAME = "name";
    private static final String DOM_ATTR_TYPE = "type";
    
    private static SAXBuilder builder = new SAXBuilder();
    
    private WareFactory() {
        this.wares = new ArrayList<Ware>();
    }


    public void parse() {
        File[] wareFolders = new File(Constants.FOLDER_WARE).listFiles();
        for (int i = 0;i<wareFolders.length;i++) {
            if(wareFolders[i].isDirectory()){
                File[] xmlDefinitions = wareFolders[i].listFiles(new FilenameFilter() {
                    
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
        List<Element> materials = doc.getRootElement().getChildren(DOM_NODE_WARE);
        for (Element element : materials) {
            
            String   type=element.getAttribute(DOM_ATTR_TYPE).getValue()
                    ,name=element.getChildText(DOM_NODE_NAME);
            
            int      value=Integer.parseInt(element.getChildText(DOM_NODE_VALUE))
                    ,weight=Integer.parseInt(element.getChildText(DOM_NODE_WEIGHT));
            
            this.wares.add(createInstance(type, name, value, weight));
        }
    }


    /**
     * Utility for createInstace Methods.
     */
    private Ware createInstance(String type , String name , int value , int weight) {
        Ware ware = null;
        switch (type) {
            default:
            case TYPE_DRINK:
                ware = new Food(name,value,weight, 0);
                break;
        }
        return ware;
    }
    

    /**
     * Return all the wares, parses if the wares have not been parsed before.
     * @return 
     */
    public ArrayList<Ware> getWares() {
        if(wares.isEmpty()){
            parse();
        }
        return wares;
    }

    public static WareFactory getInstance() {
        if(instance == null){
            instance = new WareFactory();
        }
        return instance;
    }
}
