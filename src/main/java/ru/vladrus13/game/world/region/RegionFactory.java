package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.game.basic.direction.Direction;
import ru.vladrus13.game.basic.event.region.WorldEventTeleport;
import ru.vladrus13.game.world.World;
import ru.vladrus13.game.world.components.Tile;
import ru.vladrus13.game.world.components.TileFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
        for (int i = 0; i < map.length; i++) {
            ArrayList<Tile> temp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                temp.add(TileFactory.get(map[i][j],
                        new Point((long) size * i, (long) size * j, CoordinatesType.REAL),
                        new Size(size, size, CoordinatesType.REAL),
                        parent));
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
        tiles.get(1).get(3).setOnStep(new WorldEventTeleport(2, new Point(tileSize, tileSize), Direction.LEFT));
        return (new Region(parent)).setTiles(tiles);
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
        tiles.get(1).get(1).setOnStep(new WorldEventTeleport(1, new Point(tileSize, tileSize * 3L), Direction.LEFT));
        tiles.get(5).get(1).setOnStep(new WorldEventTeleport(3, new Point(tileSize, tileSize), Direction.DOWN));
        return (new Region(parent)).setTiles(tiles);
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
        tiles.get(1).get(1).setOnStep(new WorldEventTeleport(2, new Point(tileSize * 5L, tileSize), Direction.UP));
        return (new Region(parent)).setTiles(tiles);
    }
}
