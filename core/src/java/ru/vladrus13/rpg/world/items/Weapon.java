package ru.vladrus13.rpg.world.items;

import ru.vladrus13.rpg.resources.ItemResources;

/**
 * @author vladrus13 on 26.03.2021
 **/
public abstract class Weapon extends Item {
    public Weapon(int id) {
        super(id, ItemResources.getBundle(id + ".name"), ItemResources.getBundle(id + ".description"));
    }
}
