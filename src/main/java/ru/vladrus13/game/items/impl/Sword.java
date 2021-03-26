package ru.vladrus13.game.items.impl;

import ru.vladrus13.rpg.world.items.Weapon;

/**
 * @author vladrus13 on 26.03.2021
 **/
public class Sword extends Weapon {

    public Sword() {
        super(1);
    }

    public static Sword getInstance() {
        return new Sword();
    }

    public static int getId() {
        return 1;
    }
}
