package ru.vladrus13.rpg.world.ai.command;

import ru.vladrus13.rpg.basic.direction.Direction;

public class StepCommand extends Command {
    public Direction move;

    public StepCommand(Direction move) {
        this.move = move;
    }
}
