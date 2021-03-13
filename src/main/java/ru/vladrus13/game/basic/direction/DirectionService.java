package ru.vladrus13.game.basic.direction;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.property.MainProperty;

public class DirectionService {
    private final static int tileSize = MainProperty.getInteger("world.region.tileSize");

    public static Point step(Point a, Direction direction) {
        switch (direction) {
            case DOWN:
                return new Point(a.x, a.y + tileSize, a.coordinatesType);
            case UP:
                return new Point(a.x, a.y - tileSize, a.coordinatesType);
            case LEFT:
                return new Point(a.x - tileSize, a.y, a.coordinatesType);
            case RIGHT:
                return new Point(a.x + tileSize, a.y, a.coordinatesType);
            default:
                return null;
        }
    }
}
