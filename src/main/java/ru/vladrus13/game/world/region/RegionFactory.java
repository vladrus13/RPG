package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.game.world.components.Tile;

import java.util.ArrayList;

public class RegionFactory {
    public static Region getRegion(String name, Frame parent) throws GameException {
        switch (name) {
            case "01":
                return get01(parent);
            default:
                throw new GameException("No region with this name");
        }
    }

    private static ArrayList<ArrayList<Tile>> getTiles(int[][] map, int size, Frame parent) throws GameException {
        ArrayList<ArrayList<Tile>> returned = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            ArrayList<Tile> temp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                temp.add(new Tile(
                        new Point((long) size * i, (long) size * j, CoordinatesType.REAL),
                        new Size(size, size, CoordinatesType.REAL), map[i][j],
                        parent));
            }
            returned.add(temp);
        }
        return returned;
    }

    private static Region get01(Frame parent) throws GameException {
        int[][] map = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        };
        return (new Region(parent)).setTiles(getTiles(map, MainProperty.getInteger("world.region.tileSize"), parent));
    }
}
