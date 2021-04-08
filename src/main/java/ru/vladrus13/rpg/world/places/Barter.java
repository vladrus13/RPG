package ru.vladrus13.rpg.world.places;

import ru.vladrus13.rpg.world.items.inventory.ItemType;

import java.util.ArrayList;
import java.util.Collection;

public class Barter {
    public final ArrayList<ItemType> from;
    public final ArrayList<ItemType> to;
    public int count;

    public Barter(ArrayList<ItemType> from, ArrayList<ItemType> to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    public void dec() {
        count--;
    }
}
