package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.rpg.saves.Savable;

@Savable(implemented = true)
public abstract class Ability {
    public abstract String getName();
    public abstract int getId();
    public static Ability getInstance() {
        throw new UnsupportedOperationException("Can't get instance from abstract class");
    }
}
