package ru.vladrus13.game.world.region;

import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.game.world.components.Tile;
import ru.vladrus13.graphic.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.RandomAccess;

public class Region extends UpdatedFrame {

    ArrayList<ArrayList<Tile>> tiles;

    public Region(Point start, Size size, Collection<Frame> frames, Frame parent) {
        super(start, size, frames, parent);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }

    @Override
    public int keyPressed(KeyEvent e) {
        return 0;
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return 0;
    }

    @Override
    protected void nonCheckingPause(long delay) {

    }
}
