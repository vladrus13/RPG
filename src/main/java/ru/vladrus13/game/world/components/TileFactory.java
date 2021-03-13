package ru.vladrus13.game.world.components;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;

import java.lang.reflect.InvocationTargetException;

public class TileFactory {

    public static Tile get(int id, Point start, Size size, Frame parent) throws GameException {
        try {
            return (Tile) TileFactory.class.getDeclaredMethod("get" + id, Point.class, Size.class, Frame.class)
                    .invoke(TileFactory.class, start, size, parent);
        } catch (NoSuchMethodException e) {
            throw new GameException("No tile with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new GameException(e);
        }
    }

    private static Tile get0(Point start, Size size, Frame parent) throws GameException {
        return new Tile(start, size, 0, parent).setWalkable(true);
    }

    private static Tile get1(Point start, Size size, Frame parent) throws GameException {
        return new Tile(start, size, 1, parent).setWalkable(false);
    }

    private static Tile get2(Point start, Size size, Frame parent) throws GameException {
        return new Tile(start, size, 2, parent).setWalkable(true);
    }

    private static Tile get3(Point start, Size size, Frame parent) throws GameException {
        return new Tile(start, size, 3, parent).setWalkable(true);
    }
}
