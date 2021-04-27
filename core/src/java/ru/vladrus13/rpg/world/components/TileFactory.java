package ru.vladrus13.rpg.world.components;

import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.exception.AppException;

import java.lang.reflect.InvocationTargetException;

public class TileFactory {

    public static Tile get(int id, Point start, Size size, Frame parent) throws AppException {
        try {
            return (Tile) TileFactory.class.getDeclaredMethod("get" + id, Point.class, Size.class, Frame.class)
                    .invoke(TileFactory.class, start, size, parent);
        } catch (NoSuchMethodException e) {
            throw new AppException("No tile with id: " + id);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e);
        }
    }

    private static Tile get0(Point start, Size size, Frame parent) throws AppException {
        return new Tile("empty", start, size, 0, parent).setWalkable(true);
    }

    private static Tile get1(Point start, Size size, Frame parent) throws AppException {
        return new Tile("wall", start, size, 1, parent).setWalkable(false);
    }

    private static Tile get2(Point start, Size size, Frame parent) throws AppException {
        return new Tile("upper_stair", start, size, 2, parent).setWalkable(true);
    }

    private static Tile get3(Point start, Size size, Frame parent) throws AppException {
        return new Tile("lower_stair", start, size, 3, parent).setWalkable(true);
    }
}
