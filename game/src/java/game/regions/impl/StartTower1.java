package game.regions.impl;

import game.regions.RegionFactoryImpl;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.region.Region;

import java.util.ArrayList;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class StartTower1 {
    public static Region getInstance(World parent) throws GameException {
        int[][] map = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 2, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int tileSize = MainProperty.getInteger("world.region.tileSize");
        ArrayList<ArrayList<Tile>> tiles = RegionFactoryImpl.getTiles(map, tileSize, parent);
        Region region = (new Region(1, "1", parent)).setTiles(tiles);
        region.setOnStep(new WorldEventTeleport(2, new Point(tileSize, tileSize), Direction.LEFT), new Point(1, 3));
        SaveHolder.setVariable(region, "created", "1");
        return region;
    }
}
