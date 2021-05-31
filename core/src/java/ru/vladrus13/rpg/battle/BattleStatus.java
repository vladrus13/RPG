package ru.vladrus13.rpg.battle;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;

public class BattleStatus extends Frame {
    public BattleStatus(Frame parent) {
        super("battle-status", new Point(0, 800, CoordinatesType.RATIO), new Size(1000, 200, CoordinatesType.RATIO), parent);
    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }
}
