package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.game.basic.returned.ReturnEvent;
import ru.vladrus13.game.basic.returned.ReturnInt;
import ru.vladrus13.game.world.components.Tile;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Region extends UpdatedFrame {

    ArrayList<ArrayList<Tile>> tiles;

    public Region(Frame parent) {
        super(parent.getStart(), parent.getSize(), Collections.emptyList(), parent);
        recalculate();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (ArrayList<Tile> it : tiles) {
            for (Tile jt : it) {
                jt.draw(graphics);
            }
        }
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
    }

    @Override
    public ReturnEvent keyPressed(KeyEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    public ReturnEvent mousePressed(MouseEvent e) {
        return new ReturnInt(ReturnInt.NOTHING);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {

    }

    public Region setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
        recalculate();
        return this;
    }
}
