package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.property.MainProperty;
import ru.vladrus13.game.basic.event.Event;
import ru.vladrus13.game.basic.event.region.WorldEvent;
import ru.vladrus13.game.basic.event.returned.ReturnEvent;
import ru.vladrus13.game.basic.event.returned.ReturnInt;
import ru.vladrus13.game.world.World;
import ru.vladrus13.game.world.actors.Actor;
import ru.vladrus13.game.world.actors.Hero;
import ru.vladrus13.game.world.components.Tile;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Region extends UpdatedFrame {

    private ArrayList<ArrayList<Tile>> tiles;
    private Hero hero;
    private final int tileSize = MainProperty.getInteger("world.region.tileSize");
    private final World world;

    public Region(World parent) {
        super(parent.getStart(), parent.getSize(), new ArrayList<>(), parent);
        this.world = parent;
        recalculate();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (ArrayList<Tile> it : tiles) {
            for (Tile jt : it) {
                jt.draw(graphics);
            }
        }
        hero.draw(graphics);
    }

    @Override
    public void recalculate() {
        super.recalculate();
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
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return new ReturnInt(ReturnInt.TO_MENU);
        }
        return focused.keyPressed(e);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return focused.mousePressed(e);
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
        setFocused(hero);
    }

    public boolean isWalkable(Point a) {
        a = new Point(a.x / tileSize, a.y / tileSize, a.coordinatesType);
        if (a.x < 0 || a.y < 0 || tiles.size() <= a.x || tiles.get((int) a.x).size() <= a.y) return false;
        return tiles.get((int) a.x).get((int) a.y).isWalkable();
    }

    public void onStep(Actor actor, Point a) {
        Event event = tiles.get((int) (a.x / tileSize)).get((int) (a.y / tileSize)).onStep();
        if (event != null) {
            if (event instanceof WorldEvent) {
                world.regionEvent((WorldEvent) event);
            }
        }
    }

    public World getWorld() {
        return world;
    }
}