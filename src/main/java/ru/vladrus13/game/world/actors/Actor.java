package ru.vladrus13.game.world.actors;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.game.basic.direction.Direction;
import ru.vladrus13.game.basic.direction.DirectionService;
import ru.vladrus13.game.world.region.Region;
import ru.vladrus13.graphic.Graphics;

import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Actor extends UpdatedFrame {

    private final Deque<Direction> path = new LinkedList<>();
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final String name;
    private final String resourcesName;
    private Direction walkDirection;
    private Direction lastDirection = Direction.DOWN;
    protected Region region;

    private final Map<Direction, BufferedImage> images;

    public Actor(Point start, String name, String resourcesName, Frame parent, Region region) {
        super(start, new Size(
                        MainProperty.getInteger("world.region.tileSize"),
                        MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL),
                Collections.emptyList(), parent);
        this.name = name;
        this.resourcesName = resourcesName;
        this.region = region;
        images = ActorFactory.loadActor(resourcesName);
        recalculate();
    }

    public void makeMove(Direction direction) {
        path.add(direction);
    }

    public void makeMove(Collection<Direction> directions) {
        path.addAll(directions);
    }

    private void nextDirection() {
        if (path.isEmpty()) {
            walkDirection = null;
        } else {
            walkDirection = path.pop();
            lastDirection = walkDirection;
            if (!region.isWalkable(DirectionService.step(start, walkDirection))) {
                nextDirection();
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(images.get(lastDirection), start.x, start.y, tileSize, tileSize);
    }

    public void onStep() {

    }

    public void onTrigger() {

    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        int speed = getSpeed();
        if (walkDirection == null) {
            nextDirection();
            return;
        }
        switch (walkDirection) {
            case RIGHT:
                if (start.x % tileSize + speed >= tileSize) {
                    start = new Point((start.x / tileSize + 1) * tileSize, start.y, start.coordinatesType);
                    onStep();
                    nextDirection();
                } else {
                    start = start.incX(speed);
                }
                break;
            case LEFT:
                if ((start.x - 1 + tileSize) % tileSize - speed < 0) {
                    start = new Point((start.x / tileSize) * tileSize, start.y, start.coordinatesType);
                    onStep();
                    nextDirection();
                } else {
                    start = start.incX(-speed);
                }
                break;
            case UP:
                if ((start.y - 1 + tileSize) % tileSize - speed < 0) {
                    start = new Point(start.x, (start.y / tileSize) * tileSize, start.coordinatesType);
                    onStep();
                    nextDirection();
                } else {
                    start = start.incY(-speed);
                }
                break;
            case DOWN:
                if (start.y % tileSize + speed >= tileSize) {
                    start = new Point(start.x, (start.y / tileSize + 1) * tileSize, start.coordinatesType);
                    onStep();
                    nextDirection();
                } else {
                    start = start.incY(speed);
                }
                break;
        }
    }

    public void teleport(Region region, Point point, Direction direction) {
        if (region != null) {
            setParent(region);
            this.region = region;
        }
        if (point != null) {
            start = point;
            path.clear();
        }
        if (direction != null) {
            lastDirection = direction;
        }
    }

    public abstract int getSpeed();
}
