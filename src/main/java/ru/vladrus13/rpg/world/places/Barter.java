package ru.vladrus13.rpg.world.places;

import ru.vladrus13.rpg.world.items.inventory.Items;

import java.util.ArrayList;

public class Barter {
    public final ArrayList<Items> from;
    public final ArrayList<Items> to;
    public int count;

    public Barter(ArrayList<Items> from, ArrayList<Items> to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    public void dec() {
        count--;
    }
}
