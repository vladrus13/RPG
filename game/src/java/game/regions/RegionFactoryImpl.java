package game.regions;

import game.regions.impl.StartTower1;
import game.regions.impl.StartTower2;
import game.regions.impl.StartTower3;

import static ru.vladrus13.rpg.world.factory.RegionFactory.regions;

public class RegionFactoryImpl {

    private final static int MAX_ID = 1000;

    public static void init() {
        regions = new Class<?>[MAX_ID];
        regions[1] = StartTower1.class;
        regions[2] = StartTower2.class;
        regions[3] = StartTower3.class;
    }
}
