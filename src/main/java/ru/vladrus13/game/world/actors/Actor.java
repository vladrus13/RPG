package ru.vladrus13.game.world.actors;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.game.basic.direction.Direction;
import ru.vladrus13.graphic.Graphics;

import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Actor extends UpdatedFrame {

    private final Deque<Direction> path = new LinkedList<>();
    private final int speed = 3;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final String name;
    private Direction direction = Direction.DOWN;

    private final Map<Direction, BufferedImage> images;

    public Actor(Point start, String name, Frame parent) {
        super(start, new Size(
                        MainProperty.getInteger("world.region.tileSize"),
                        MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL),
                Collections.emptyList(), parent);
        this.name = name;
        images = ActorFactory.loadActor(name);
        recalculate();
    }

    private void makeMove(Direction direction) {
        path.add(direction);
    }

    private void makeMove(Collection<Direction> directions) {
        path.addAll(directions);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(images.get(direction), start.x, start.y, tileSize, tileSize);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {

    }
}
