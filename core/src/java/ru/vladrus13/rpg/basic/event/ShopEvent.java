package ru.vladrus13.rpg.basic.event;

import ru.vladrus13.jgraphic.basic.event.Event;
import ru.vladrus13.rpg.world.places.Barter;

public class ShopEvent extends Event {
    public final Barter barter;

    public ShopEvent(Barter barter) {
        this.barter = barter;
    }
}
