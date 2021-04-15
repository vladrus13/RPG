package ru.vladrus13.rpg.world.actors;

import org.json.JSONObject;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEvent;
import ru.vladrus13.rpg.resources.ActorResources;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.world.items.inventory.Inventory;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

@Savable(implemented = true)
public abstract class Actor extends UpdatedFrame {
    protected static final int tileSize = MainProperty.getInteger("world.region.tileSize");
    @SaveConstante(name = "inventory")
    public Inventory inventory = new Inventory();
    protected final Deque<Direction> path = new LinkedList<>();
    @SaveConstante(name = "realName", constructor = 1)
    protected final String name;
    @SaveConstante(name = "name", constructor = 1)
    protected final String resourcesName;
    private final Map<Direction, BufferedImage> images;
    @SaveConstante(name = "direction")
    public Direction direction = Direction.DOWN;
    protected Direction walkDirection;
    protected Region region;
    protected RegionEvent onTrigger;
    protected RegionEvent onStep;
    @SaveConstante(name = "standard_status")
    protected Status standardStatus = new Status();
    @SaveConstante(name = "real_status")
    protected Status realStatus = new Status();
    public boolean untouchable = false;
    public int command = 0;

    public Actor(JSONObject jsonObject) {
        this(jsonObject.getString("name"),
                new Point(jsonObject.getJSONObject("start").getLong("x"), jsonObject.getJSONObject("start").getLong("y")),
                jsonObject.getString("realName"), null);

    }

    public Actor(String systemName, Point start, String name, Region region) {
        super(systemName, start, new Size(
                        MainProperty.getInteger("world.region.tileSize"),
                        MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL),
                region);
        this.name = name;
        this.resourcesName = systemName;
        this.region = region;
        this.inventory.setActor(this);
        images = ActorResources.loadActor(resourcesName);
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
            direction = walkDirection;
            if (!region.isWalkable(DirectionService.step(start, walkDirection))) {
                nextDirection();
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(images.get(direction), start.x, start.y, tileSize, tileSize);
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
            this.direction = direction;
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

    public Status getStandardStatus() {
        return standardStatus;
    }

    public void setRealStatus(Status realStatus) {
        this.realStatus = realStatus;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
