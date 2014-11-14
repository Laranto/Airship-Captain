package common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

public class Constants {
    public static final String FOLDER_MATERIAL = "resources/material/";
    public static final String FOLDER_OBJECTS = "resources/object/";
    
    
    public static final String XML_FILE_ENDING = ".xml";
    
    
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    /** Tile Size in Pixels*/
    public static final int TILE_SIZE = 16;
    public static final int AIRSHIP_WIDTH_TILES = (WINDOW_WIDTH/2)/TILE_SIZE;
    public static final int AIRSHIP_HEIGHT_TILES = WINDOW_HEIGHT/TILE_SIZE;
    
    
    public static final Color COLOR_SKYBLUE = new Color(128,218,235);
    public static final Color BUTTON_BACKGROUND_INACTIVE = Color.LIGHT_GRAY.brighter();
    public static final Color BUTTON_BACKGROUND_DELETE_ACTIVE = Color.RED;
    public static final Color BUTTON_BACKGROUND_ACTIVE = Color.LIGHT_GRAY;
    public static final Object BUTTON_PROPERTY_ID = "id";
    
    
    /**
     * Default Orientation pointing right
     */
    @SuppressWarnings("all") //Hush, everything is oke
    public static final Vector<Integer> ENTITY_ORIENTATION_RIGHT=new Vector<>(new ArrayList(){{add(1);add(0);}}
    );
    
    @SuppressWarnings("all") //Hush, everything is oke
    public static final Vector<Integer> ENTITY_ORIENTATION_LEFT=new Vector<>(new ArrayList(){{add(-1);add(0);}}
            );
    
    @SuppressWarnings("all") //Hush, everything is oke
    public static final Vector<Integer> ENTITY_ORIENTATION_UP=new Vector<>(new ArrayList(){{add(0);add(-1);}}
            );
    
    @SuppressWarnings("all") //Hush, everything is oke
    public static final Vector<Integer> ENTITY_ORIENTATION_DOWN=new Vector<>(new ArrayList(){{add(0);add(1);}}
            );
    
    
}
