package ru.vladrus13.rpg.world.factory;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.components.TileFactory;
import ru.vladrus13.rpg.world.region.Region;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class RegionFactory {

    public static Class<?>[] regions;

    public static Region getRegion(int id, World parent) throws AppException {
        try {
            return (Region) regions[id].getMethod("getInstance", World.class).invoke(regions[id], parent);
        } catch (NoSuchMethodException e) {
            throw new AppException("No region with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e);
        }
    }

    public static ArrayList<ArrayList<Tile>> getTiles(int[][] map, int size, Frame parent) throws AppException {
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

}
