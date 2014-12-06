package handler;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import model.GameState;
import common.enums.MenuItemEnum;
import controller.WindowController;

public class SettingsStrategy extends HandlerStrategy {
    private MenuItemEnum activeMenu;

    private ArrayList<JTextField> settingsList;
    
    public SettingsStrategy() {
        settingsList = new ArrayList<JTextField>();
    }


    @Override
    public void mouseEvent(MouseEvent e) {
        if(this.activeMenu != null)
        {
            switch (this.activeMenu) {
            case SAVE_GAME_SETTINGS:
                GameState.getInstance().getAirship().getCaptain().setName(settingsList.get(0).getText());   //captains name
                GameState.getInstance().getAirship().setName(settingsList.get(1).getText());                //ships name
                if(!GameState.getInstance().getAirship().isJoined())
                {
                    WindowController.showConstruction();
                }else{
                    WindowController.showHarbor();
                }
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void publishProperty(Object activeMenu) {
        this.activeMenu = (MenuItemEnum) activeMenu;
        this.mouseEvent(null);
    }

    @Override
    public void keyEvent(KeyEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void addSettingField(JTextField field)
    {
        this.settingsList.add(field);
    }
    

}
