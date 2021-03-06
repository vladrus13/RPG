package game.regions.impl;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.factory.ActorFactory;
import ru.vladrus13.rpg.world.factory.RegionFactory;
import ru.vladrus13.rpg.world.region.Region;
import ru.vladrus13.rpg.world.region.WarZone;

import java.util.ArrayList;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class StartTower3 {

    private final static int tileSize = MainProperty.getInteger("world.region.tileSize");

    public static void setActors(Region region) {
        ArrayList<Actor> actors = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            actors.add(ActorFactory.createActor(10, new Point((long) i * tileSize, 5L * tileSize), region));
        }
        for (int i = 2; i <= 8; i += 2) {
            actors.add(ActorFactory.createActor(11, new Point((long) i * tileSize, 7L * tileSize), region));
        }
        region.setActors(actors);
    }

    public static Region getInstance(World parent) throws AppException {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int tileSize = MainProperty.getInteger("world.region.tileSize");
        ArrayList<ArrayList<Tile>> tiles = RegionFactory.getTiles(map, tileSize, parent);
        Region region = (new WarZone(3, "3", parent)).setTiles(tiles);
        region.setOnStep(new WorldEventTeleport(2, new Point(tileSize, tileSize * 5L), Direction.UP), new Point(1, 1));
        setActors(region);
        return region;
    }
}
