package ru.vladrus13.rpg.ai.world.command;

public class WaitCommand extends Command {
    public final int time;

    public WaitCommand(int time) {
        this.time = time;
    }
}
