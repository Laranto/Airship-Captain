package common.language;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * RessourceBundle uses property Files to load the correct language in the Application  
 * 
 */
public class Language {
    
    private static ResourceBundle resources;
    
    private Language(){
        try{
            resources = ResourceBundle.getBundle(System.getProperty("user.dir")+"\\AirshipCaptain", Locale.getDefault());
        }catch(MissingResourceException e){
            resources = ResourceBundle.getBundle("common.language.AirshipCaptain", Locale.getDefault());
        }
    }

    public static ResourceBundle getInstance() {
        if(resources == null){
            new Language();
        }
        return resources;
    }
    
    public String getString(String key) {
        return resources.getString(key);
    }
}
