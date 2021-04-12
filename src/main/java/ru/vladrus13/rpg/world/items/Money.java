package ru.vladrus13.rpg.world.items;

import ru.vladrus13.game.items.ItemFactory;

public abstract class Money extends Item {
    public Money(int id) {
        super(id, ItemFactory.getBundle(id + ".name"), ItemFactory.getBundle(id + ".description"));
    }
}
