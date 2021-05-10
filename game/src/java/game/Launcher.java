package game;

import game.abilities.AbilityFactoryImpl;
import game.actors.ActorFactoryImpl;
import game.items.ItemFactoryImpl;
import game.regions.RegionFactoryImpl;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.Game;

import java.io.IOException;
import java.util.logging.LogManager;

public class Launcher {
    public static void main(String[] args) {
        if (args.length == 0) {
            try {
                LogManager.getLogManager().readConfiguration(Launcher.class.getResourceAsStream("/properties/logging.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainProperty.read();
            ActorFactoryImpl.init();
            ItemFactoryImpl.init();
            AbilityFactoryImpl.init();
            RegionFactoryImpl.init();
            new Game();
        }
    }
}
