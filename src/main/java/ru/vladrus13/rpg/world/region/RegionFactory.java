package ru.vladrus13.rpg.world.region;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.event.world.WorldEventTeleport;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.ActorFactory;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.components.TileFactory;
import ru.vladrus13.rpg.world.items.ItemFactory;
import ru.vladrus13.rpg.world.items.RegionItem;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RegionFactory {
    public static Region getRegion(int id, World parent) throws GameException {
        try {
            return (Region) RegionFactory.class.getDeclaredMethod("get" + id, World.class)
                    .invoke(RegionFactory.class, parent);
        } catch (NoSuchMethodException e) {
            throw new GameException("No region with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    private static ArrayList<ArrayList<Tile>> getTiles(int[][] map, int size, Frame parent) throws GameException {
        ArrayList<ArrayList<Tile>> returned = new ArrayList<>();
        int maxLength = Arrays.stream(map).mapToInt(it -> it.length).max().orElseThrow();
        for (int i = 0; i < maxLength; i++) {
            ArrayList<Tile> temp = new ArrayList<>();
            for (int j = 0; j < map.length; j++) {
                if (map[j].length > i) {
                    temp.add(TileFactory.get(map[j][i],
                            new Point((long) size * i, (long) size * j, CoordinatesType.REAL),
                            new Size(size, size, CoordinatesType.REAL),
                            parent));
                }
            }
            returned.add(temp);
        }
        return returned;
    }

    @SuppressWarnings("unused")
    private static Region get1(World parent) throws GameException {
        int[][] map = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 2, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int tileSize = MainProperty.getInteger("world.region.tileSize");
        ArrayList<ArrayList<Tile>> tiles = getTiles(map, tileSize, parent);
        Region region = (new Region("1", parent)).setTiles(tiles);
        region.setOnStep(new WorldEventTeleport(2, new Point(tileSize, tileSize), Direction.LEFT), new Point(1, 3));
        return region;
    }

    @SuppressWarnings("unused")
    private static Region get2(World parent) throws GameException {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int tileSize = MainProperty.getInteger("world.region.tileSize");
        ArrayList<ArrayList<Tile>> tiles = getTiles(map, tileSize, parent);
        Region region = (new Region("2", parent)).setTiles(tiles);
        region.setOnStep(new WorldEventTeleport(1, new Point(tileSize * 3L, tileSize), Direction.LEFT), new Point(1, 1));
        region.setOnStep(new WorldEventTeleport(3, new Point(tileSize, tileSize), Direction.DOWN), new Point(5, 1));
        region.setActors(new ArrayList<>(Collections.singletonList(ActorFactory.createActor("pirate", new Point(6L * tileSize, 4L * tileSize), region))));
        region.setItems(new ArrayList<>(Collections.singletonList(new RegionItem(new Point(8L * tileSize, tileSize), region, ItemFactory.get(1)))));
        return region;
    }

    @SuppressWarnings("unused")
    private static Region get3(World parent) throws GameException {
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
        ArrayList<ArrayList<Tile>> tiles = getTiles(map, tileSize, parent);
        Region region = (new Region("3", parent)).setTiles(tiles);
        region.setOnStep(new WorldEventTeleport(2, new Point(tileSize, tileSize * 5L), Direction.UP), new Point(1, 1));
        return region;
    }
}
