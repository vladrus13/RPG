package ru.vladrus13.rpg.basic.event.region;

import ru.vladrus13.rpg.world.actors.Actor;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class RegionEventOnStep extends RegionEvent {
    public final Actor actor;

    public RegionEventOnStep(Actor actor) {
        this.actor = actor;
    }
}
