package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.rpg.world.region.Region;

public abstract class AbilityActor extends Ability {
    public abstract void activate(Actor from, Actor to, Region region);
}