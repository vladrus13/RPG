package ru.vladrus13.rpg.world.items;

import ru.vladrus13.game.items.ItemFactory;

/**
 * @author vladrus13 on 26.03.2021
 **/
public abstract class Weapon extends Item {
    public Weapon(int id) {
        super(id, ItemFactory.getBundle(id + ".name"), ItemFactory.getBundle(id + ".description"));
    }
}
