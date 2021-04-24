package ru.vladrus13.rpg.world.actors;

import org.json.JSONObject;
import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.Direction;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEvent;
import ru.vladrus13.rpg.basic.event.region.RegionEventDie;
import ru.vladrus13.rpg.resources.ActorResources;
import ru.vladrus13.rpg.saves.Savable;
import ru.vladrus13.rpg.saves.SaveConstante;
import ru.vladrus13.rpg.saves.SaveHolder;
import ru.vladrus13.rpg.world.factory.ActorFactory;
import ru.vladrus13.rpg.world.items.inventory.Inventory;
import ru.vladrus13.rpg.world.region.Region;

import java.awt.image.BufferedImage;
import java.util.*;

@Savable(implemented = true)
public abstract class Actor extends UpdatedFrame {
    protected static final int tileSize = MainProperty.getInteger("world.region.tileSize");
    @SaveConstante(name = "id", constructor = 1)
    protected final int id;
    protected final Deque<Direction> path = new LinkedList<>();
    public final String name;
    @SaveConstante(name = "systemName", constructor = 1)
    protected final String systemName;
    private final Map<Direction, BufferedImage> images;
    @SaveConstante(name = "inventory")
    public Inventory inventory = new Inventory();
    @SaveConstante(name = "direction")
    public Direction direction = Direction.DOWN;
    public boolean untouchable = false;
    public int command = 0;
    @SaveConstante(name = "standard_status")
    public Status standardStatus;
    public Status realStatus;
    @SaveConstante(name = "abilities", constructor = 1)
    public Map<String, Ability> abilities = new HashMap<>();
    protected Direction walkDirection;
    protected Region region;
    protected RegionEvent onTrigger;
    protected RegionEvent onStep;

    public Actor(int id, String systemName, Point start, String name, Region region) {
        super("actor" + id, start, new Size(
                        MainProperty.getInteger("world.region.tileSize"),
                        MainProperty.getInteger("world.region.tileSize"), CoordinatesType.REAL),
                region);
        this.id = id;
        this.systemName = systemName;
        this.name = name;
        this.region = region;
        this.inventory.setActor(this);
        images = ActorResources.loadActor(systemName);
        recalculate();
    }

    public static Actor getInstance(Object object) {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException("Actor must be instanced from JSONObject");
        }
        JSONObject jsonObject = (JSONObject) object;
        Actor actor = ActorFactory.createActor(jsonObject.getInt("id"), new Point(jsonObject.getJSONObject("start").getLong("x"), jsonObject.getJSONObject("start").getLong("y")),
                null);
        JSONObject abil = ((JSONObject) object).getJSONObject("abilities");
        for (String key : abil.keySet()) {
            Ability ability = (Ability) SaveHolder.recursiveSet(abil.getJSONObject(key), Ability.class);
            actor.abilities.put(ability.getName(), ability);
        }
        return actor;
    }

    public Map<String, Object> getPrivateFields() {
        return Map.of("start", start.copy());
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
            } else {
                region.moveActor(this, start, DirectionService.step(start, walkDirection));
            }
        }
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        graphics.drawImage(images.get(direction), start.x, start.y, tileSize, tileSize);
    }

    public void onStep() {
    }

    public void onTrigger() {
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        if (region == null) {
            return;
        }
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

    public void setParent(Region region) {
        this.setParent((Frame) region);
        this.region = region;
    }

    public void updateStatus() {
        inventory.reloadActor();
    }

    public boolean isSpecialAbitily(Ability ability) {
        return false;
    }

    public void onPhysical(int damage) {
        this.realStatus.hp -= damage;
        if (realStatus.hp <= 0) {
            callEvent(new RegionEventDie(this, start));
        }
    }
}
