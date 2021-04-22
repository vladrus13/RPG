package ru.vladrus13.rpg.ai.world.command;

import ru.vladrus13.jgraphic.bean.Point;

public class TargetPoint implements Target {
    public final Point target;

    public TargetPoint(Point target) {
        this.target = target;
    }
}
