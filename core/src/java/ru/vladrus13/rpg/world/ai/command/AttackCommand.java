package ru.vladrus13.rpg.world.ai.command;

import ru.vladrus13.rpg.world.actors.Actor;

public class AttackCommand extends Command {
    public Actor from;
    public Actor to;

    public AttackCommand(Actor from, Actor to) {
        this.from = from;
        this.to = to;
    }
}
