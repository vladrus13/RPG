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
import java.util.ArrayList;

public class World extends Frame {

    Text text = new Text(
            new Point(10, 10, CoordinatesType.REAL),
            new Size(100, 100, CoordinatesType.REAL), this, "REAL", FontService.getFont("Inventory", 20), Color.BLACK, Text.TextAlign.CENTER);

    Text next = new Text(
            new Point(20, 50, CoordinatesType.RATIO),
            new Size(100, 100, CoordinatesType.RATIO), this, "RATIO", FontService.getFont("Inventory", 20), Color.BLACK, Text.TextAlign.CENTER);

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

    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }
}
