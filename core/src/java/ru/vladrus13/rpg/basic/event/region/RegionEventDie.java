package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.world.actors.Actor;

public class RegionEventDie extends RegionEvent {
    public final Actor died;
    public final Point position;

    public RegionEventDie(Actor died, Point position) {
        this.died = died;
        this.position = position;
    }
}
