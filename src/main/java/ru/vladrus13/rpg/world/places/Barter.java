package ru.vladrus13.rpg.world.places;

import ru.vladrus13.rpg.world.items.inventory.ItemType;

import java.util.Collection;

public class Barter {
    public final Collection<ItemType> from;
    public final Collection<ItemType> to;
    public int count;

    public Barter(Collection<ItemType> from, Collection<ItemType> to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    public void dec() {
        count--;
    }
}
