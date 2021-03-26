package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.game.actors.ActorFactory;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEvent;
import ru.vladrus13.rpg.world.items.inventory.Inventory;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public abstract class Actor extends UpdatedFrame {

    protected final Deque<Direction> path = new LinkedList<>();
    protected static final int tileSize = MainProperty.getInteger("world.region.tileSize");
    protected final String name;
    protected final String resourcesName;
    protected Direction walkDirection;
    public Direction lastDirection = Direction.DOWN;
    protected Region region;
    protected RegionEvent onTrigger;
    protected RegionEvent onStep;
    public final Inventory inventory = new Inventory();

    private final Map<Direction, BufferedImage> images;

    public Actor(String systemName, Point start, String name, Region region) {
        super(systemName, start, new Size(
                        MainProperty.getInteger("world.region.tileSize"),
                        MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL),
                region);
        this.name = name;
        this.resourcesName = systemName;
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
        if (onStep != null) region.invokeRegionEvent(onStep);
    }

    public void onTrigger() {
        if (onTrigger != null) region.invokeRegionEvent(onTrigger);
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

    @Override
    public void recalculateChildes() {

    }

    public abstract Actor copy();

    public void setOnTrigger(RegionEvent onTrigger) {
        this.onTrigger = onTrigger;
    }
}
