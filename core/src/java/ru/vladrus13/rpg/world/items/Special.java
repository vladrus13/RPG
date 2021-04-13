package ru.vladrus13.rpg.world.items;

import ru.vladrus13.rpg.resources.ItemResources;

public abstract class Special extends Item {
    public Special(int id) {
        super(id, ItemResources.getBundle(id + ".name"), ItemResources.getBundle(id + ".description"));
    }
}
