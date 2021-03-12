package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.world.actors.Hero;
import ru.vladrus13.game.world.components.Tile;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Region extends UpdatedFrame {

    ArrayList<ArrayList<Tile>> tiles;
    Hero hero;

    public Region(Frame parent) {
        super(parent.getStart(), parent.getSize(), new ArrayList<>(), parent);
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
}
