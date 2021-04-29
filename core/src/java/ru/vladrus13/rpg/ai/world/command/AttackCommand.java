package ru.vladrus13.rpg.ai.world.command;

import ru.vladrus13.rpg.world.actors.Ability;
import ru.vladrus13.rpg.world.actors.Actor;

public class AttackCommand extends Command {
    public final Actor from;
    public final Actor to;
    public final Ability ability;

    public AttackCommand(Actor from, Actor to, Ability ability) {
        this.from = from;
        this.to = to;
        this.ability = ability;
    }
}
