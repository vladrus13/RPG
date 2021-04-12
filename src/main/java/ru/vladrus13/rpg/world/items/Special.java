package ru.vladrus13.rpg.world.items;

import ru.vladrus13.game.items.ItemFactory;

public abstract class Special extends Item {
    public Special(int id) {
        super(id, ItemFactory.getBundle(id + ".name"), ItemFactory.getBundle(id + ".description"));
    }
}
