package ru.vladrus13.rpg.world.actors;

import ru.vladrus13.jgraphic.bean.Point;
import ru.vladrus13.rpg.world.region.Region;

public abstract class AbilityPoint extends Ability {
    public abstract void activate(Actor from, Point to, Region region);
}