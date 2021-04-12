package ru.vladrus13.rpg.world.region;

import ru.vladrus13.game.actors.impl.Hero;
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
import ru.vladrus13.rpg.basic.event.region.RegionEventFocused;
import ru.vladrus13.rpg.basic.event.region.RegionEventOnStep;
import ru.vladrus13.rpg.basic.event.world.WorldEvent;
import ru.vladrus13.rpg.world.World;
import ru.vladrus13.rpg.world.actors.Actor;
import ru.vladrus13.rpg.world.components.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class Region extends UpdatedFrame {

    protected final int id;
    private ArrayList<ArrayList<Tile>> tiles;
    public Hero hero;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final World world;

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
        }
        if (hero != null) {
            hero.draw(graphics);
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
        if (hero != null) {
            hero.recalculate();
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
        hero.update(delay);
    }

    public Region setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
        recalculate();
        return this;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        addFocused(hero);
    }

    public void setActors(Collection<Actor> actors) {
        for (Actor actor : actors) {
            tiles
                    .get((int) (actor.getStart().x / tileSize))
                    .get((int) (actor.getStart().y / tileSize)).actor = actor;
        }
    }

    public void setItems(ArrayList<RegionItem> items) {
        for (RegionItem item : items) {
            tiles
                    .get((int) (item.getStart().x / tileSize))
                    .get((int) (item.getStart().y / tileSize)).regionItem = item;
        }
    }

    public boolean isWalkable(Point a) {
        a = new Point(a.x / tileSize, a.y / tileSize, a.coordinatesType);
        if (a.x < 0 || a.y < 0 || tiles.size() <= a.x || tiles.get((int) a.x).size() <= a.y) return false;
        return tiles.get((int) a.x).get((int) a.y).isWalkable();
    }

    public void onStep(Actor actor) {
        Point a = actor.getStart();
        Event event = tiles.get((int) (a.x / tileSize)).get((int) (a.y / tileSize)).onStep();
        if (event != null) {
            if (event instanceof WorldEvent) {
                world.invokeWorldEvent((WorldEvent) event);
            }
        }
    }

    public void onActivate(Actor actor, Point a) {
        Tile tile = tiles.get((int) (a.x / tileSize)).get((int) (a.y / tileSize));
        Point nextPoint = DirectionService.step(actor.getStart(), actor.lastDirection);
        Tile nextTile = tiles.get((int) (nextPoint.x / tileSize)).get((int) (nextPoint.y / tileSize));
        if (tile.regionItem != null) {
            actor.inventory.addItem(tile.regionItem.item);
            tile.regionItem = null;
        } else {
            if (nextTile.regionItem != null) {
                actor.inventory.addItem(nextTile.regionItem.item);
                nextTile.regionItem = null;
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
        if (regionEvent instanceof RegionEventFocused) {
            if (((RegionEventFocused) regionEvent).isRemove) {
                if (((RegionEventFocused) regionEvent).focused == null) {
                    removeFocused();
                } else {
                    focused.remove(((RegionEventFocused) regionEvent).focused);
                    // TODO make focused great again
                    // removeFocused(((RegionEventFocused) regionEvent).focused);
                }
                if (((RegionEventFocused) regionEvent).drawing) {
                    removeChild(((RegionEventFocused) regionEvent).focused);
                }
            } else {
                if (((RegionEventFocused) regionEvent).drawing) {
                    addChild(((RegionEventFocused) regionEvent).focused);
                }
                addFocused(((RegionEventFocused) regionEvent).focused);
            }
        }
    }
}
