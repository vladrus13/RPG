package ru.vladrus13.game.basic.event.world;

import ru.vladrus13.core.bean.Point;
import ru.vladrus13.game.basic.direction.Direction;

public class WorldEventTeleport extends WorldEvent {

    private final int id;
    private final Point point;
    private final Direction direction;

    public WorldEventTeleport(int id, Point start, Direction direction) {
        this.id = id;
        this.point = start;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }
}
