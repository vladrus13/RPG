package ru.vladrus13.rpg.world.region;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.jgraphic.property.MainProperty;
import ru.vladrus13.rpg.basic.direction.DirectionService;
import ru.vladrus13.rpg.basic.event.region.RegionEvent;
import ru.vladrus13.rpg.basic.event.region.RegionEventDie;
import ru.vladrus13.rpg.basic.event.region.RegionEventDrawing;
import ru.vladrus13.rpg.basic.event.region.RegionEventOnStep;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.actors.Enemy;
import ru.vladrus13.rpg.world.components.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class Region extends UpdatedFrame {

    public final int id;
    protected final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final World world;
    private final ArrayList<UpdatedFrame> updatedFrames = new ArrayList<>();
    public Actor hero;
    private ArrayList<ArrayList<Tile>> tiles;

    public Region(int id, String name, World parent) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
        this.id = id;
        this.world = parent;
        recalculateChildes();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        if (tiles != null) {
            for (ArrayList<Tile> it : tiles) {
                for (Tile jt : it) {
                    jt.draw(graphics);
                }
            }
            for (ArrayList<Tile> it : tiles) {
                for (Tile jt : it) {
                    jt.drawIn(graphics);
                }
            }
        }
        for (Frame frame : childes) {
            frame.draw(graphics);
        }
    }

    @Override
    public void recalculateChildes() {
        if (tiles != null) {
            for (ArrayList<Tile> it : tiles) {
                for (Tile jt : it) {
                    jt.recalculate();
                }
            }
        }
        for (Frame frame : childes) {
            frame.recalculate();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        focused.getFirst().keyPressed(e);
    }

    @Override
    public void callEvent(Event event) {
        if (event instanceof RegionEvent) {
            this.invokeRegionEvent((RegionEvent) event);
            return;
        }
        parent.callEvent(event);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        focused.getFirst().mousePressed(e);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {
        boolean isHero = false;
        for (ArrayList<Tile> it : tiles) {
            for (Tile jt : it) {
                if (jt.actor != null) {
                    jt.actor.update(delay);
                    if (!(jt.actor instanceof Enemy)) {
                        isHero = true;
                    }
                }
            }
        }
        if (!isHero) {
            logger.warning("Can't find hero");
        }
        for (UpdatedFrame frame : updatedFrames) {
            frame.update(delay);
        }
    }

    public Region setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
        recalculate();
        return this;
    }

    public void setHero(Actor hero) {
        this.hero = hero;
        getTile(hero.getStart()).actor = hero;
        addFocused(hero);
    }

    public void setActors(Collection<Actor> actors) {
        for (Actor actor : actors) {
            getTile(actor.getStart()).actor = actor;
        }
    }

    public void setItems(ArrayList<RegionItem> items) {
        for (RegionItem item : items) {
            getTile(item.getStart()).regionItems.add(item);
        }
    }

    public boolean isWalkable(Point a) {
        a = new Point(a.x / tileSize, a.y / tileSize, a.coordinatesType);
        if (a.x < 0 || a.y < 0 || tiles.size() <= a.x || tiles.get((int) a.x).size() <= a.y) return false;
        return tiles.get((int) a.x).get((int) a.y).isWalkable();
    }

    public void moveActor(Actor actor, Point before, Point after) {
        if (getActor(before) == null) {
            throw new IllegalStateException("Can't move non-existing actor");
        }
        if (getActor(after) != null && !getActor(after).untouchable) {
            throw new IllegalStateException("Can't move to existing actor");
        }
        getTile(before).actor = null;
        getTile(after).actor = actor;
    }

    public Tile getTile(Point point) {
        return tiles.get((int) (point.x / tileSize)).get((int) (point.y / tileSize));
    }

    public Actor getActor(Point point) {
        return getTile(point).actor;
    }

    public void onStep(Actor actor) {
        Point a = actor.getStart();
        Event event = getTile(a).onStep();
        if (event != null) {
            if (event instanceof WorldEvent) {
                world.invokeWorldEvent((WorldEvent) event);
            }
        }
    }

    public void onActivate(Actor actor, Point a) {
        Tile tile = getTile(a);
        Point nextPoint = DirectionService.step(actor.getStart(), actor.direction);
        Tile nextTile = getTile(nextPoint);
        if (!tile.regionItems.isEmpty()) {
            actor.inventory.addItems(tile.regionItems.removeFirst().item);
        } else {
            if (!nextTile.regionItems.isEmpty()) {
                actor.inventory.addItems(nextTile.regionItems.removeFirst().item);
            } else {
                if (nextTile.actor != null) {
                    nextTile.actor.onTrigger();
                }
            }
        }
    }

    public void setOnStep(Event event, Point a) {
        tiles.get((int) a.y).get((int) a.x).setOnStep(event);
    }

    public World getWorld() {
        return world;
    }

    public void invokeRegionEvent(RegionEvent regionEvent) {
        if (regionEvent instanceof RegionEventOnStep) {
            onStep(((RegionEventOnStep) regionEvent).actor);
        }
        if (regionEvent instanceof RegionEventDrawing) {
            RegionEventDrawing regionEventDrawing = (RegionEventDrawing) regionEvent;
            if (regionEventDrawing.isRemove) {
                if (regionEventDrawing.isFocused) {
                    if (regionEventDrawing.frame == null) {
                        removeFocused();
                    } else {
                        focused.remove(regionEventDrawing.frame);
                    }
                }
                if (regionEventDrawing.drawing) {
                    removeChild(regionEventDrawing.frame);
                }
                if (regionEventDrawing.frame instanceof UpdatedFrame) {
                    updatedFrames.remove((UpdatedFrame) regionEventDrawing.frame);
                }
            } else {
                if (regionEventDrawing.drawing) {
                    addChild(regionEventDrawing.frame);
                }
                if (regionEventDrawing.isFocused) {
                    addFocused(regionEventDrawing.frame);
                }
                if (regionEventDrawing.frame instanceof UpdatedFrame) {
                    updatedFrames.add((UpdatedFrame) regionEventDrawing.frame);
                }
            }
        }
        if (regionEvent instanceof RegionEventDie) {
            if (getTile(((RegionEventDie) regionEvent).position).actor != ((RegionEventDie) regionEvent).died) {
                throw new IllegalStateException("Can't remove another actor");
            }
            getTile(((RegionEventDie) regionEvent).position).actor = null;
        }
    }
}
