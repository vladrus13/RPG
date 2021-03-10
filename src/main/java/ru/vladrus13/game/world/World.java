package ru.vladrus13.game.world;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.UpdatedFrame;
import ru.vladrus13.core.basic.components.Text;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.game.basic.ReturnKeys;
import ru.vladrus13.graphic.Graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class World extends UpdatedFrame {

    private final Text text = new Text(
            new Point(10, 10, CoordinatesType.REAL),
            new Size(100, 100, CoordinatesType.REAL), "REAL", "Inventory", new Size(20, 0, CoordinatesType.REAL), Color.BLACK, Text.TextAlign.CENTER, this);

    private final Text next = new Text(
            new Point(500, 500, CoordinatesType.RATIO),
            new Size(500, 500, CoordinatesType.RATIO), "RATIO", "Inventory", new Size(100, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER, this);

    public World(int width, int height) throws GameException {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), new ArrayList<>(), null);
        frames.add(text);
        frames.add(next);
        recalculate();
    }

    @Override
    public void nonCheckingDraw(Graphics graphics) {
        for (Drawn drawn : frames) {
            drawn.draw(graphics);
        }
    }

    @Override
    public int keyPressed(KeyEvent e) {
        return ReturnKeys.NOTHING;
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return ReturnKeys.NOTHING;
    }

    @Override
    protected void nonCheckingPause(long delay) {

    }
}
