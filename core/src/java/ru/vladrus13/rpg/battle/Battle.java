package ru.vladrus13.rpg.battle;

import ru.vladrus13.graphic.Graphics;
import ru.vladrus13.jgraphic.basic.Frame;
import ru.vladrus13.jgraphic.basic.UpdatedFrame;
import ru.vladrus13.jgraphic.bean.CoordinatesType;
import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.jgraphic.bean.Size;
import ru.vladrus13.rpg.basic.Reloaded;

public class Battle extends UpdatedFrame implements Reloaded {

    BattleStatus battleStatus;

    public Battle(String name, Frame parent) {
        super(name, new Point(0, 0, CoordinatesType.RATIO), new Size(1000, 1000, CoordinatesType.RATIO), parent);
    }

    @Override
    protected void nonCheckingUpdate(long delay) {

    }

    @Override
    protected void nonCheckingDraw(Graphics graphics) {

    }

    @Override
    public void reload() {

    }
}
