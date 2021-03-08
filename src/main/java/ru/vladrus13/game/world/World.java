package ru.vladrus13.game.world;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.components.Text;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;
import ru.vladrus13.core.exception.GameException;
import ru.vladrus13.core.services.FontService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class World extends Frame {

    Text text = new Text(
            new Point(10, 10, CoordinatesType.REAL),
            new Size(100, 100, CoordinatesType.REAL), this, "REAL", "Inventory", new Size(20, 0, CoordinatesType.REAL), Color.BLACK, Text.TextAlign.CENTER);

    Text next = new Text(
            new Point(500, 500, CoordinatesType.RATIO),
            new Size(500, 500, CoordinatesType.RATIO), this, "RATIO", "Inventory", new Size(100, 0, CoordinatesType.RATIO), Color.BLACK, Text.TextAlign.CENTER);

    public World(int width, int height) throws GameException {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), new ArrayList<>());
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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
