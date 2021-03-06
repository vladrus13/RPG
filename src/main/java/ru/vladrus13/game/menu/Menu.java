package ru.vladrus13.game.menu;

import ru.vladrus13.core.basic.Drawn;
import ru.vladrus13.core.basic.Frame;
import ru.vladrus13.core.basic.components.Choose;
import ru.vladrus13.core.bean.CoordinatesType;
import ru.vladrus13.core.bean.Point;
import ru.vladrus13.core.bean.Size;

import java.awt.*;
import java.util.ArrayList;

public class Menu extends Frame {

    public Menu(int width, int height) {
        super(new Point(0, 0, CoordinatesType.REAL), new Size(width, height, CoordinatesType.REAL), new ArrayList<>());
        Choose choose = new Choose(new Point(0, 0, CoordinatesType.REAL), new Size(1000, 1000, CoordinatesType.RATIO),
                new ArrayList<>(), this);
        frames.add(choose);
        recalculate();
    }

    public void recalculate(int width, int height) {
        size = new Size(width, height, CoordinatesType.REAL);
        recalculate();
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {
        for (Drawn drawn : frames) {
            drawn.draw(graphics);
        }
    }
}
