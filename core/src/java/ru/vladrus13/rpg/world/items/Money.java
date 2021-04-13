package ru.vladrus13.rpg.world.items;

import ru.vladrus13.rpg.resources.ItemResources;

public abstract class Money extends Item {
    public Money(int id) {
        super(id, ItemResources.getBundle(id + ".name"), ItemResources.getBundle(id + ".description"));
    }
}
