package ru.vladrus13.rpg.menu;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

public class Settings extends Frame {
    public Settings(String name, Frame parent) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }
}
