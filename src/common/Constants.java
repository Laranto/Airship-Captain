package common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.economy.Market;
import model.navigation.Harbor;

public class Constants {

    public static final int GAME_TICK_SPEED = 100;
    
    public static final String FOLDER_MATERIAL = "resources/material/";
    public static final String FOLDER_OBJECTS = "resources/object/";
    public static final String FOLDER_WARE = "resources/ware/";
    public static final String FOLDER_GAME_DATA = "resources/save/";
    public static final String FOLDER_AIRSHIPS = "resources/ship/";
    public static final String FOLDER_ENEMY_AIRSHIPS = "resources/enemy_ship/";
    
    public static final String NAVIGATION_BACKGROUND_IMAGE = "resources/panels/img/map.jpg";
    public static final String HARBOR_BACKGROUND_IMAGE = "resources/panels/img/zeppelin.jpg";
    public static final String ZEPPLIN_COCKPIT_BACKGROUND_IMAGE = "resources/panels/img/zepplin_cockpit.png";
    public static final String COMPASS_IMAGE = "resources/panels/img/compass.png";
    public static final String MARKET_BACKGROUND_IMAGE = "resources/panels/img/market.jpg";
    public static final String CANNONBALL_IMAGE = "resources/object/weapons/img/cannonball.png";
    public static final int CANNONBALL_SIZE = 10;
    
    
    public static final String XML_FILE_ENDING = ".xml";
    
    
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int CONTROL_PANEL_WIDTH = 300;
    public static final int CONTROL_PANEL_HEIGHT = 100;
    /** Tile Size in Pixels*/
    public static final int TILE_SIZE = 16;
    public static final int AIRSHIP_WIDTH_TILES = (WINDOW_WIDTH/2)/TILE_SIZE;
    public static final int AIRSHIP_HEIGHT_TILES = WINDOW_HEIGHT/TILE_SIZE;
    public static final String AIRSHIP_NAME_DEFAULT = "Noname";
    
    
    public static final double AIRSHIP_MAX_WEIGHT = 40000.0;  //for calculating the speed, 
    public static final double AIRSHIP_MAX_SPEED = 26.0;
    public static final double AIRSHIP_MIN_SPEED = 2.0;
    
    public static final double CAPTAIN_START_MONEY = 5000;
    public static final String CAPTAIN_NAME_DEFAULT = "Me the Captain";
    public static final int MOVE_UP = KeyEvent.VK_UP;
    public static final int MOVE_DOWN = KeyEvent.VK_DOWN;
    public static final int MOVE_LEFT = KeyEvent.VK_LEFT;
    public static final int MOVE_RIGHT = KeyEvent.VK_RIGHT;
    public static final double CAPTAIN_MOVE_SPEED = 2;
    public static final String CAPTAIN_HAT_IMAGE = "resources/captain/captain_hat.png";
    
    public static final int OPPORTUNITY_MAX_MONEY = 20;
    
    
    public static final Color COLOR_SKYBLUE = new Color(128,218,235);
    public static final Color BUTTON_BACKGROUND_INACTIVE = Color.LIGHT_GRAY.brighter();
    public static final Color BUTTON_BACKGROUND_DELETE_ACTIVE = Color.RED;
    public static final Color BUTTON_BACKGROUND_ACTIVE = Color.LIGHT_GRAY;
    public static final Object BUTTON_PROPERTY_ID = "id";
    
    public static final Color HARBOR_CIRCLE_BACKGROUND_ACTIVE = Color.RED;
    public static final Color HARBOR_CIRCLE_BACKGROUND_NEXT_DESTINATION = Color.BLUE;
    public static final Color HARBOR_CIRCLE_BACKGROUND = Color.WHITE;
    public static final Color HARBOR_CIRCLE_BORDER = Color.BLACK;
    public static final int HARBOR_CIRCLE_DIAMETER = 20;
    public static final String HARBOR_ICON = "resources/panels/img/anchor.png";
    @SuppressWarnings("serial")
    public static final List<Harbor> HARBORS = new ArrayList<Harbor>(){{
        add(new Harbor(new Market(), new Point(310, 340), false));
        add(new Harbor(new Market(), new Point(110, 500), false));
        add(new Harbor(new Market(), new Point(160, 410), false));
        add(new Harbor(new Market(), new Point(210, 300), false));
        add(new Harbor(new Market(), new Point(280, 200), false));
        add(new Harbor(new Market(), new Point(280, 400), false));
        add(new Harbor(new Market(), new Point(290,  60), false));
        add(new Harbor(new Market(), new Point(300, 490), false));
        add(new Harbor(new Market(), new Point(330, 260), false));
        add(new Harbor(new Market(), new Point(450, 200), false));
        add(new Harbor(new Market(), new Point(460, 500), false));
        add(new Harbor(new Market(), new Point(490, 290), false));
        add(new Harbor(new Market(), new Point(500,  90), false));
        add(new Harbor(new Market(), new Point(500, 400), false));
        add(new Harbor(new Market(), new Point(600,  50), false));
        add(new Harbor(new Market(), new Point(600, 270), false));
        add(new Harbor(new Market(), new Point(600, 345), false));
        add(new Harbor(new Market(), new Point(610, 200), false));
        add(new Harbor(new Market(), new Point(720, 275), false));
    }};
    
    public static final double WARE_STANDARD_AMOUNT = 100;
    public static final double WARE_MAX_INFLATION_FACTOR = 5;
    public static final double WARE_SELL_RATIO = 0.75;    
    public static final double OBJECT_SELL_RATIO = 0.75;
    public static final int MAX_TRADEABLE_ITEMS = 10;
    
    public static final String FILE_ENDING_GAME = "asc";
    public static final String FILE_ENDING_SHIP = "ascs";

    
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

    
	public static final Stroke STROKE_DASHED = new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{10f},0);
	public static final Stroke STROKE_SOLID = new BasicStroke(4);

    public static final double BATTLE_FINISH_RATIO = 10; //in percent  10 -> 10%
    public static final int BATTLE_PROFIT_RATIO = 7;  // min=1 max=infinite :: divides the ship durability by this number and adds it as money




}