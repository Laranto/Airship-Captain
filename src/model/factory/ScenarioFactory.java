package model.factory;

import model.Airship;
import model.Scenario;

public class ScenarioFactory {

    public static Scenario build() {
        Scenario szenario = new Scenario();
        //TODO generate a szenario for the player
        //what are the reaction of the KI
        //how much damage can he take until he gets destroyed/backs down
        szenario.setEnemy(generateEnemy());
        return szenario;
    }

    private static Airship generateEnemy() {
        Airship enemy = new Airship();
        for(int i = 0;i<10;i++){
            enemy.placeMaterial(MaterialFactory.getInstance().getMaterials().get(0), 4, 4+i);
        }
        for(int i = 0;i<10;i++){
            for(int j = 0;j<5;j++)
            enemy.placeMaterial(MaterialFactory.getInstance().getMaterials().get(1), 5+j, 4+i);
        }
        for(int i = 0;i<10;i++){
            enemy.placeMaterial(MaterialFactory.getInstance().getMaterials().get(0), 10, 4+i);
        }
        return enemy;
    }

}
