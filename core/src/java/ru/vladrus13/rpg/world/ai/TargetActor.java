package ru.vladrus13.rpg.world.ai;

import ru.vladrus13.rpg.world.actors.Actor;

public class TargetActor implements Target {
    public final Actor target;

    public TargetActor(Actor target) {
        this.target = target;
    }
}
