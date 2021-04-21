package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.rpg.world.region.Region;

public abstract class AbilitySelf extends Ability {
    public abstract void activate(Actor from, Region region);
}
