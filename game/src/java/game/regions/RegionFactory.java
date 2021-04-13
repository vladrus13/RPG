package game.regions;

import game.regions.impl.StartTower1;
import game.regions.impl.StartTower2;
import game.regions.impl.StartTower3;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.GameException;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.components.Tile;
import ru.vladrus13.rpg.world.components.TileFactory;
import ru.vladrus13.rpg.world.region.Region;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class RegionFactory {

    private final static int MAX_ID = 1000;

    private final static Class<?>[] regions = new Class<?>[MAX_ID];

    static {
        regions[1] = StartTower1.class;
        regions[2] = StartTower2.class;
        regions[3] = StartTower3.class;
    }

    public static Region getRegion(int id, World parent) throws GameException {
        try {
            return (Region) regions[id].getMethod("getInstance", World.class).invoke(regions[id], parent);
        } catch (NoSuchMethodException e) {
            throw new GameException("No region with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    public static ArrayList<ArrayList<Tile>> getTiles(int[][] map, int size, Frame parent) throws GameException {
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
