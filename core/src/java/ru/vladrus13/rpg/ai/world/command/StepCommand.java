package ru.vladrus13.rpg.ai.world.command;

import ru.vladrus13.rpg.basic.direction.Direction;

public class StepCommand extends Command {
    public final Direction move;

    public StepCommand(Direction move) {
        this.move = move;
    }
}
