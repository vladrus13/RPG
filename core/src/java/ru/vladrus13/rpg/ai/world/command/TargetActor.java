package ru.vladrus13.rpg.ai.world.command;

import ru.vladrus13.rpg.world.actors.Actor;

public class TargetActor implements Target {
    public final Actor target;

    public TargetActor(Actor target) {
        this.target = target;
    }
}
